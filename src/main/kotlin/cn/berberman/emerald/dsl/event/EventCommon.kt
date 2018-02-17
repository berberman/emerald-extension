package cn.berberman.emerald.dsl.event

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.emptyListener
import cn.berberman.emerald.extension.info
import cn.berberman.emerald.extension.plugin
import cn.berberman.emerald.extension.pluginManager
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.RegisteredListener
import org.bukkit.plugin.SimplePluginManager

/**
 * Register event.
 * @param packingEvent event listener
 */
fun <T : Event> registerEvent(packingEvent: PackingEvent<T>) {
	pluginManager.registerEvent(packingEvent.type, emptyListener, packingEvent.eventPriority,
			packingEvent.executor, plugin)
	if (Emerald.debug)
		info("register event listener: ${packingEvent.type.simpleName}")
}

/**
 * Register event.
 * @param supplier A lambda supplies event listener
 */
fun <T : Event> registerEvent(supplier: () -> PackingEvent<T>) {
	registerEvent(supplier())
}

internal fun getEventExecutor(registeredListener: RegisteredListener): EventExecutor {
	val field = RegisteredListener::class.java.let {
		it.declaredFields.first { it.name == "executor" }
	}.apply { isAccessible = true }
	return field.get(registeredListener) as EventExecutor
}

/**
 * Unregister event
 * @param packingEvent registered event
 */
fun <T : Event> unregisterEvent(packingEvent: PackingEvent<T>) {
	getEventListeners(packingEvent.type).let {
		it.registeredListeners.first { getEventExecutor(it) == packingEvent.executor }
				.let(it::unregister)
	}
}

/**
 * Unregister event
 * @param supplier a lambda supplies registered event
 */
fun <T : Event> unregisterEvent(supplier: () -> PackingEvent<T>) {
	unregisterEvent(supplier())
}

@Suppress("UNCHECKED_CAST")
internal fun getEventListeners(type: Class<out Event>): HandlerList {
	val getRegistrationClass = SimplePluginManager::class.java.let {
		val m = it.declaredMethods.first { it.name == "getRegistrationClass" }
		m.isAccessible = true
		m
	}
	return (getRegistrationClass(pluginManager, type) as Class<out Event>).let {
		it.getDeclaredMethod("getHandlerList")(it) as HandlerList
	}
}

@DslMarker
private annotation class EventBuilder
