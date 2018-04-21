package cn.berberman.emerald.dsl.event

import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.plugin.EventExecutor

/**
 * Packing a event Listener with org.bukkit.plugin.EventExecutor
 * @param type eventListener type
 * @param eventPriority eventPriority
 * @param block action when eventListener execute
 * @author berberman
 */
class PackingEvent<in T : Event>(val type: Class<out Event>,
                                 val eventPriority: EventPriority,
                                 val ignoredCancelled: Boolean,
                                 private val block: (T) -> Unit) {
	internal var isRegistered = false

	@Suppress("UNCHECKED_CAST")
	internal val executor: EventExecutor = EventExecutor { _, event ->
		block(event as T)
	}
}