package cn.berberman.emerald.ui

import cn.berberman.emerald.dsl.event.event
import cn.berberman.emerald.dsl.event.registerEvent
import cn.berberman.emerald.dsl.event.unregisterEvent
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory

data class UI internal constructor(private val inventory: Inventory) {

	val slots = mutableMapOf<Int, Slot>()

	private val listener = InventoryClick()
	fun openUI(player: Player) {
		player.closeInventory()
		listener.register()
		player.openInventory(inventory)
	}

	fun addSlot(slot: Slot) {
		if (slots.containsKey(slot.solt)) throw IllegalArgumentException()
		slots[slot.solt] = slot
	}

	fun addSlots(vararg slot: Slot) {
		slot.any { slots.containsKey(it.solt) }.takeIf { it }?.run { throw IllegalArgumentException() }
		slots.putAll(slots)
	}

	internal inner class InventoryClick {
		var registered = false

		private val clickEvent = event<InventoryClickEvent>(EventPriority.MONITOR,
				true) {
			if (clickedInventory != this@UI.inventory) return@event
			slots[rawSlot]?.let {
				it.onClick(this)
				it.modifiable.takeIf { it }?.let { isCancelled = true }
			}
		}
		private val closeEvent = event<InventoryCloseEvent>(EventPriority.MONITOR,
				true) {
			if (inventory != this@UI.inventory) return@event
			unregister()
		}

		internal fun unregister() {
			if (!registered) return
			unregisterEvent(clickEvent)
			unregisterEvent(closeEvent)
			registered = false
		}

		internal fun register() {
			if (registered) return
			registerEvent(clickEvent)
			registerEvent(closeEvent)
			registered = true
		}

	}
}

data class Slot(internal val solt: Int) {

	var modifiable = false

	var onClick: (InventoryClickEvent) -> Unit = {}
		private set

	fun onClick(block: (InventoryClickEvent) -> Unit) {
		onClick = block
	}


}