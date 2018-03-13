package cn.berberman.emerald.dsl.event

import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.plugin.EventExecutor

/**
 * Packing a createEventListener with org.bukkit.plugin.EventExecutor
 * @param type createEventListener type
 * @param eventPriority eventPriority
 * @param block action when createEventListener execute
 * @author berberman
 */
class PackingEvent<in T : Event>(val type: Class<out Event>,
                                 val eventPriority: EventPriority,
                                 val ignoredCancelled: Boolean,
                                 private val block: (T) -> Unit) {
	internal var isRegistered = false

	@Suppress("UNCHECKED_CAST")
	val executor: EventExecutor = EventExecutor { _, event ->
		block(event as T)
	}
}