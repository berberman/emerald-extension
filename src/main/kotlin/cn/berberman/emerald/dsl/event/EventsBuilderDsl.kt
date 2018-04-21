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
	 * Build a packing eventListener.
	 * 	@param T target eventListener
	 * @param eventPriority Event Priority, default is normal
	 * @param block DSL part of building eventListener listeners
	 */
	@EventBuilder
	inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL,
	                                     ignoreCancelled: Boolean = false,
	                                     noinline block: T.() -> Unit) =
			PackingEvent(T::class.java, eventPriority, ignoreCancelled, block).let(events::add).takeIf { it }
					?: throw IllegalArgumentException("Event listener already existed")

	@PublishedApi
	internal val events = mutableSetOf<PackingEvent<*>>()

	fun registerAll() = events.forEach(PackingEvent<*>::register)

	fun unregisterAll() = events.forEach(PackingEvent<*>::unregister)

}

/**
 * Register events.
 * @param block DSL part of building eventListener listeners
 */
fun registerEventListeners(block: EventsBuilder.() -> Unit) =
		EventsBuilder().apply(block).also(EventsBuilder::registerAll)

/**
 * Build a packing eventListener.
 * @param T target eventListener
 * @param eventPriority Event Priority, default is normal
 * @param block DSL part of building eventListener listeners
 * @return the eventListener listener that you build
 */
inline fun <reified T : Event> eventListener(eventPriority: EventPriority = EventPriority.NORMAL,
                                             ignoreCancelled: Boolean = false,
                                             noinline block: T.() -> Unit) =
		PackingEvent(T::class.java, eventPriority, ignoreCancelled, block)