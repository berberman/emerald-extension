package cn.berberman.emerald.events.player

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

sealed class PacketEvent(var msg: Any) : Event(), Cancellable {


	override fun setCancelled(cancel: Boolean) {
		cancelled = cancel
	}

	override fun isCancelled(): Boolean = cancelled

	private var cancelled = false


	class PacketPlayInEvent(msg: Any) : PacketEvent(msg) {
		override fun getHandlers(): HandlerList = getHandlerList()

		companion object {
			private val handlers = HandlerList()

			@JvmStatic
			fun getHandlerList() = handlers
		}
	}

	class PacketPlayOutEvent(msg: Any) : PacketEvent(msg) {
		override fun getHandlers(): HandlerList = getHandlerList()

		companion object {
			private val handlers = HandlerList()

			@JvmStatic
			fun getHandlerList() = handlers
		}
	}
}