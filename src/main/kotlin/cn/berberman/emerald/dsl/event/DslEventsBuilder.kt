package cn.berberman.emerald.dsl.event

import org.bukkit.event.Event
import org.bukkit.event.EventPriority

/**
 * A DSL structure to build events.
 * @author berberman
 * @see PackingEvent
 *
 */
@EventBuilder
class EventsBuilder internal constructor() {
	/**
	 * Build a packing event.
	 * 	@param T target event
	 * @param eventPriority Event Priority, default is normal
	 * @param block DSL part of building event listeners
	 */
	@EventBuilder
	inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL, ignoreCancelled: Boolean = false, noinline block: T.() -> Unit) = run {
		PackingEvent(T::class.java, eventPriority, ignoreCancelled, block).let(events::add)
		Unit
	}

	@PublishedApi
	internal val events = mutableListOf<PackingEvent<*>>()

}

/**
 * Register events.
 * @param block DSL part of building event listeners
 */
fun registerEvents(block: EventsBuilder.() -> Unit) =
		EventsBuilder().apply(block).apply {
			events.forEach {
				registerEvent(it)
			}
		}

/**
 * Build a packing event.
 * @param T target event
 * @param eventPriority Event Priority, default is normal
 * @param block DSL part of building event listeners
 * @return the event listener that you build
 */
inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL, ignoreCancelled: Boolean = false, noinline block: T.() -> Unit) =
		PackingEvent(T::class.java, eventPriority, ignoreCancelled, block)