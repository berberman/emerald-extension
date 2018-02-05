package cn.berberman.emerald.extension.dsl.event

import cn.berberman.emerald.extension.Emerald
import cn.berberman.emerald.extension.extension.emptyListener
import cn.berberman.emerald.extension.extension.logger
import cn.berberman.emerald.extension.extension.plugin
import cn.berberman.emerald.extension.extension.pluginManager
import org.bukkit.event.Event
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

/**
 * Packing a event with [org.bukkit.plugin.EventExecutor]
 * @param type event type
 * @param eventPriority eventPriority
 * @param block action when event execute
 * @author berberman
 * @see org.bukkit.plugin.PluginManager.registerEvent
 */
class PackingEvent<in T : Event>(private val type: Class<out Event>,
                                 private val eventPriority: EventPriority,
                                 private val block: (T) -> Unit) {
	@Suppress("UNCHECKED_CAST")
	internal fun register() {
		pluginManager.registerEvent(type, emptyListener, eventPriority,
				{ _: Listener, event ->
					block(event as T)
				}, plugin)
		if (Emerald.debug)
			logger.info("注册事件监听器：${type.simpleName}")

	}
}