package cn.berberman.emerald.extension.dsl.event

import org.bukkit.event.Event
import org.bukkit.event.EventPriority

class DSLEventScope {
	inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL, noinline block: T.() -> Unit) {
		PackingEvent(T::class.java, eventPriority, block).let(EventHolder::add)
	}
}

internal fun buildEvents(block: DSLEventScope.() -> Unit) =
		DSLEventScope().block()

