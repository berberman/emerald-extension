package cn.berberman.emerald.extension.dsl.event

import org.bukkit.event.Event
import org.bukkit.event.EventPriority

/**
 * A DSL structure to build event.
 * @author berberman
 * @see PackingEvent
 * @see EventHolder
 */
class DSLEventScope internal constructor() {
	/**
	 * Build event function.
	 * @param T target event
	 * @param eventPriority priority of event listener, default is NORMAL
	 * @see EventPriority
	 */
	inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL, noinline block: T.() -> Unit) {
		PackingEvent(T::class.java, eventPriority, block).let(accessHolderAdd)
	}

	@PublishedApi
	internal val accessHolderAdd
		get() = EventHolder::add

}

internal fun buildEvents(block: DSLEventScope.() -> Unit) =
		DSLEventScope().block()

