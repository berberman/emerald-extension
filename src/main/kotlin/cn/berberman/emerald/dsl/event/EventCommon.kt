package cn.berberman.emerald.dsl.event

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.emptyListener
import cn.berberman.emerald.extension.logger
import cn.berberman.emerald.extension.plugin
import cn.berberman.emerald.extension.pluginManager
import org.bukkit.event.Event
import org.bukkit.event.HandlerList
import org.bukkit.plugin.EventExecutor
import org.bukkit.plugin.IllegalPluginAccessException
import org.bukkit.plugin.RegisteredListener
import org.bukkit.plugin.SimplePluginManager

internal fun <T : Event> registerEvent(packingEvent: PackingEvent<T>) {
	pluginManager.registerEvent(packingEvent.type, emptyListener, packingEvent.eventPriority,
			packingEvent.executor, plugin)
	if (Emerald.debug)
		logger.info("注册事件监听器：${packingEvent.type.simpleName}")
	getEventListeners(packingEvent.type).registeredListeners
}

internal fun getEventExecutor(registeredListener: RegisteredListener): EventExecutor {
	val field = RegisteredListener::class.java.let {
		it.declaredFields.firstOrNull { it.name == "executor" }
				?: throw  IllegalPluginAccessException("Internal Error")
	}
	return field.get(registeredListener) as EventExecutor
}

internal fun <T : Event> unregisterEvent(packingEvent: PackingEvent<T>) {
	getEventListeners(packingEvent.type).let {
		it.registeredListeners.first { getEventExecutor(it) == packingEvent.executor }
				.let(it::unregister)
	}
}

internal fun getEventListeners(type: Class<out Event>): HandlerList {
	val getRegistrationClass = SimplePluginManager::class.java.let {
		val m = it.declaredMethods.firstOrNull { it.name == "getRegistrationClass" }
				?: throw  IllegalPluginAccessException("Internal Error")
		m.isAccessible = true
		m
	}
	try {
		val method = (getRegistrationClass(type) as Class<*>/*as Class<out Event>*/)
				.getDeclaredMethod("getHandlerList")
		method.isAccessible = true
		return method.invoke(null) as HandlerList
	} catch (e: Exception) {
		throw IllegalPluginAccessException(e.toString())
	}

}