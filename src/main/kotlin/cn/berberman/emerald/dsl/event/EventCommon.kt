package cn.berberman.emerald.dsl.event

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.info
import cn.berberman.emerald.reflection.invokeMethod
import cn.berberman.emerald.util.EmeraldUtil.emptyListener
import cn.berberman.emerald.util.EmeraldUtil.plugin
import cn.berberman.emerald.util.EmeraldUtil.pluginManager
import cn.berberman.emerald.util.ReflectionUtil
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
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
	if (Emerald.debug)
		info("registerPlugin event listener: ${type.simpleName}")
}

/**
 * Register event Listener.
 * @param supplier A lambda supplies eventListener listener
 */
fun <T : Event> registerEvent(supplier: () -> PackingEvent<T>) {
	supplier().register()
}

internal fun RegisteredListener.getEventExecutor(): EventExecutor {
//	val field = RegisteredListener::class.java.let {
//		it.declaredFields.first { it.name == "executor" }
//	}.apply { isAccessible = true }
//	return field.get(this) as EventExecutor
	return ReflectionUtil.getField("executor", this)
}

/**
 * Unregister event Listener
 */
fun <T : Event> PackingEvent<T>.unregister() {
	if (!isRegistered) return
	type.getEventListeners().let {
		it.registeredListeners.first { it.getEventExecutor() == executor }
				.let(it::unregister)
	}
	isRegistered = false
}

/**
 * Unregister eventListener
 * @param supplier a lambda supplies isRegistered eventListener
 */
fun <T : Event> unregisterEvent(supplier: () -> PackingEvent<T>) {
	supplier().unregister()
}

@Suppress("UNCHECKED_CAST")
internal fun Class<out Event>.getEventListeners(): HandlerList {
//	val getRegistrationClass = SimplePluginManager::class.java.let {
//		//		val m = it.declaredMethods.first { it.name == "getRegistrationClass" }
//		val m = it.getDeclaredMethod("getRegistrationClass", Class::class.java)
//		m.isAccessible = true
//		m
//	}
	val getRegistrationClass = ReflectionUtil.getMethod(SimplePluginManager::class.java,
			"getRegistrationClass", Class::class.java)
	return (getRegistrationClass(pluginManager, this) as Class<out Event>).let {
		it.invokeMethod(it, "getHandlerList") as HandlerList
	}
}

