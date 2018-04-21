package cn.berberman.emerald.dsl.event

import cn.berberman.emerald.extension.debug
import cn.berberman.emerald.reflection.invokeMethod
import cn.berberman.emerald.util.EmeraldUtil.emptyListener
import cn.berberman.emerald.util.EmeraldUtil.plugin
import cn.berberman.emerald.util.EmeraldUtil.pluginManager
import cn.berberman.emerald.util.ReflectionUtil
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.event.HandlerList.getRegisteredListeners
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.RegisteredListener
import org.bukkit.plugin.SimplePluginManager

/**
 * Register event Listener.
 */
fun <T : Event> PackingEvent<T>.register() {
	if (isRegistered) return
	pluginManager.registerEvent(type, emptyListener, eventPriority,
			executor, plugin, ignoredCancelled)
	isRegistered = true
	debug("registerPlugin event listener: ${type.simpleName}")
}

internal fun RegisteredListener.getEventExecutor(): EventExecutor =
		ReflectionUtil.getField("executor", this)


/**
 * Unregister event Listener
 */
fun <T : Event> PackingEvent<T>.unregister() {
	if (!isRegistered) return
	getRegisteredListeners(plugin).find { it.getEventExecutor() == executor }?.let {
		this.type.let { ReflectionUtil.getField<HandlerList>(it, "handlers", null) }.unregister(it)
		isRegistered = false
		debug("unregisterPlugin event listener: ${type.simpleName}")
	}
}

@Suppress("UNCHECKED_CAST")
@Deprecated("low performance")
internal fun Class<out Event>.getEventListeners(): HandlerList {
	val getRegistrationClass = ReflectionUtil.getMethod(SimplePluginManager::class.java,
			"getRegistrationClass", Class::class.java)
	return (getRegistrationClass(pluginManager, this) as Class<out Event>).let {
		it.invokeMethod(it, "getHandlerList") as HandlerList
	}
}

