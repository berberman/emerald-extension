package cn.berberman.emerald.coroutine

import kotlinx.coroutines.experimental.*

/**
 * Creates new coroutine and returns its future result as an implementation of [Deferred].
 *
 * The running coroutine is cancelled when the resulting object is [cancelled][Job.cancel].
 *
 * By default, the coroutine is immediately scheduled for execution.
 * Other options can be specified via `start` parameter. See [CoroutineStart] for details.
 * An optional [start] parameter can be set to [CoroutineStart.LAZY] to start coroutine _lazily_. In this case,,
 * the resulting [Deferred] is created in _new_ state. It can be explicitly started with [start][Job.start]
 * function and will be started implicitly on the first invocation of [join][Job.join] or [await][Deferred.await].
 *
 * @param start coroutine start option. The default value is [CoroutineStart.DEFAULT].
 * @param block the coroutine code.
 */
fun <T> async(
		start: CoroutineStart = CoroutineStart.DEFAULT,
		block: suspend CoroutineScope.() -> T
) = async(SpigotCoroutineDispatcher, start, null, block)

/**
 * Launches new coroutine without blocking current thread and returns a reference to the coroutine as a [Job].
 * The coroutine is cancelled when the resulting job is [cancelled][Job.cancel].
 *
 * By default, the coroutine is immediately scheduled for execution.
 * Other options can be specified via `start` parameter. See [CoroutineStart] for details.
 * An optional [start] parameter can be set to [CoroutineStart.LAZY] to start coroutine _lazily_. In this case,
 * the coroutine [Job] is created in _new_ state. It can be explicitly started with [start][Job.start] function
 * and will be started implicitly on the first invocation of [join][Job.join].
 *
 * Uncaught exceptions in this coroutine cancel parent job in the context by default
 * (unless [CoroutineExceptionHandler] is explicitly specified), which means that when `launch` is used with
 * the context of another coroutine, then any uncaught exception leads to the cancellation of parent coroutine.
 *
 * See [newCoroutineContext] for a description of debugging facilities that are available for newly created coroutine.
 *
 * @param start coroutine start option. The default value is [CoroutineStart.DEFAULT].
 * @param block the coroutine code.
 */
fun launch(
		start: CoroutineStart = CoroutineStart.DEFAULT,
		block: suspend CoroutineScope.() -> Unit
) = launch(SpigotCoroutineDispatcher, start, null, block)
