package cn.berberman.emerald.ui

import cn.berberman.emerald.dsl.event.createEventListener
import cn.berberman.emerald.dsl.event.register
import cn.berberman.emerald.dsl.event.unregister
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
		private var registered = false

		private val clickEvent = createEventListener<InventoryClickEvent>(EventPriority.MONITOR,
				true) {
			if (clickedInventory != this@UI.inventory) return@createEventListener
			slots[rawSlot]?.let {
				it.onClick(this)
				it.modifiable.takeIf { it }?.let { isCancelled = true }
			}
		}
		private val closeEvent = createEventListener<InventoryCloseEvent>(EventPriority.MONITOR,
				true) {
			if (inventory != this@UI.inventory) return@createEventListener
			unregister()
		}

		internal fun unregister() {
			if (!registered) return
			clickEvent.unregister()
			closeEvent.unregister()
			registered = false
		}

		internal fun register() {
			if (registered) return
			clickEvent.register()
			closeEvent.register()
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