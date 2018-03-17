package cn.berberman.emerald.dsl.event

import cn.berberman.emerald.dsl.annotation.EventBuilder
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
	 * Build a packing createEventListener.
	 * 	@param T target createEventListener
	 * @param eventPriority Event Priority, default is normal
	 * @param block DSL part of building createEventListener listeners
	 */
	@EventBuilder
	inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL,
	                                     ignoreCancelled: Boolean = false,
	                                     noinline block: T.() -> Unit) =
			PackingEvent(T::class.java, eventPriority, ignoreCancelled, block).let(events::add).takeIf { it }
					?: throw IllegalArgumentException("Event Listener Already Existed")


	@PublishedApi
	internal val events = mutableSetOf<PackingEvent<*>>()

}

/**
 * Register events.
 * @param block DSL part of building createEventListener listeners
 */
fun registerEvents(block: EventsBuilder.() -> Unit) =
		EventsBuilder().apply(block).apply {
			events.forEach(PackingEvent<*>::register)
		}

/**
 * Build a packing createEventListener.
 * @param T target createEventListener
 * @param eventPriority Event Priority, default is normal
 * @param block DSL part of building createEventListener listeners
 * @return the createEventListener listener that you build
 */
inline fun <reified T : Event> createEventListener(eventPriority: EventPriority = EventPriority.NORMAL, ignoreCancelled: Boolean = false, noinline block: T.() -> Unit) =
		PackingEvent(T::class.java, eventPriority, ignoreCancelled, block)