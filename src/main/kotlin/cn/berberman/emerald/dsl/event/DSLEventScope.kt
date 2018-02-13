package cn.berberman.emerald.dsl.event

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.logger
import org.bukkit.event.Event
import org.bukkit.event.EventPriority

/**
 * A DSL structure to build events.
 * @author berberman
 * @see PackingEvent
 *
 */
class DSLEventScope internal constructor() {
	/**
	 * Build a packing event.
	 * 	@param T target event
	 * @param eventPriority Event Priority, default is normal
	 * @param block DSL part of building event listeners
	 */
	inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL, noinline block: T.() -> Unit) = run {
		PackingEvent(T::class.java, eventPriority, block).let(events::add)
		Unit
	}

	@PublishedApi
	internal val events = mutableListOf<PackingEvent<*>>()

}

/**
 * Register events.
 * @param block DSL part of building event listeners
 */
fun registerEvents(block: DSLEventScope.() -> Unit) =
		DSLEventScope().apply(block).apply {
			events.forEach {
				registerEvent(it)
				if (Emerald.debug)
					logger.info("register event listener：${it.type.simpleName}")
			}
		}

/**
 * Build a packing event.
 * @param T target event
 * @param eventPriority Event Priority, default is normal
 * @param block DSL part of building event listeners
 * @return the event listener that you build
 */
inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL, noinline block: T.() -> Unit) =
		PackingEvent(T::class.java, eventPriority, block)