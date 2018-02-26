package cn.berberman.emerald.events

import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import java.net.URI

class ServerNetWorkEvent(var uri: URI) : Event(), Cancellable {
	override fun setCancelled(cancel: Boolean) {
		cancelled = cancel
	}

	override fun isCancelled(): Boolean = cancelled

	override fun getHandlers(): HandlerList = handlerList

	private var cancelled = false

	companion object {
		@JvmStatic
		val handlerList = HandlerList()
	}
}