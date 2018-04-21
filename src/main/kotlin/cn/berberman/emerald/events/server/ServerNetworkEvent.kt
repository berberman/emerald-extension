package cn.berberman.emerald.events.server

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import java.net.URI

class ServerNetworkEvent(var uri: URI) : Event(), Cancellable {
	override fun setCancelled(cancel: Boolean) {
		cancelled = cancel
	}

	override fun isCancelled(): Boolean = cancelled

	private var cancelled = false

	override fun getHandlers(): HandlerList = getHandlerList()

	companion object {
		private val handlers = HandlerList()

		@JvmStatic
		fun getHandlerList() = handlers
	}
}