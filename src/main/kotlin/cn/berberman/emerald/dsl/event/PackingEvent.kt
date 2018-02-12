package cn.berberman.emerald.dsl.event

import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.plugin.EventExecutor

/**
 * Packing a event with org.bukkit.plugin.EventExecutor
 * @param type event type
 * @param eventPriority eventPriority
 * @param block action when event execute
 * @author berberman
 */
class PackingEvent<in T : Event>(val type: Class<out Event>,
                                 val eventPriority: EventPriority,
                                 private val block: (T) -> Unit) {

	@Suppress("UNCHECKED_CAST")
	val executor: EventExecutor = EventExecutor { _, event ->
		block(event as T)
	}
}