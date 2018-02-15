package cn.berberman.emerald.coroutine

import kotlinx.coroutines.experimental.CoroutineScope
import org.bukkit.scheduler.BukkitTask
import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Provide coroutine context with bukkit task
 * get it from [CoroutineScope.coroutineContext]
 * @see CoroutineScope.coroutineContext
 * @param task bukkit task
 * @author berberman
 */
class BukkitTaskCoroutineContext(private val task: BukkitTask) : AbstractCoroutineContextElement(BukkitTaskCoroutineContext),
                                                                 BukkitTask by task {
	companion object Key : CoroutineContext.Key<BukkitTaskCoroutineContext>
}