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
//	@Suppress("UNCHECKED_CAST")
//	@Deprecated("GG")
//	internal fun register() {
//		pluginManager.registerEvent(type, emptyListener, eventPriority,
//				{ _: Listener, event ->
//					block(event as T)
//				}, plugin)
//		if (Emerald.debug)
//			logger.info("注册事件监听器：${type.simpleName}")
//	}
}