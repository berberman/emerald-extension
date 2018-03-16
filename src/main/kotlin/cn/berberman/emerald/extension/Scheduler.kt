package cn.berberman.emerald.extension

import cn.berberman.emerald.util.EmeraldUtil.plugin
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

/**
 * Start an async loop.
 * *Asynchronous tasks should never access any API in Bukkit. Great care
 * should be taken to assure the thread-safety of asynchronous tasks.*
 * <p>
 * Returns a task that will run asynchronously.
 * @param delay the ticks to wait before running the task, default is 0
 * @param block action, keep running if it returns true
 * @return a BukkitTask that contains the id number
 */
fun bukkitAsyncLoop(delay: Long = 0L, block: () -> Boolean) = bukkitAsync(delay) {
	var result = block()
	while (result)
		result = block()
}

/**
 * Start an async task.
 * *Asynchronous tasks should never access any API in Bukkit. Great care
 * should be taken to assure the thread-safety of asynchronous tasks.*
 * <p>
 * Returns a task that will run asynchronously.
 * @param delay the ticks to wait before running the task, default is 0
 * @param block action to run
 * @return a BukkitTask that contains the id number
 */
fun bukkitAsync(delay: Long = 0L, block: () -> Unit): BukkitTask = Bukkit.getScheduler()
		.let {
			if (delay != 0L) it.runTaskAsynchronously(plugin, block)
			else it.runTaskLaterAsynchronously(plugin, block, delay)
		}

/**
 * Start a task run on main thread.
 * Returns a task that will run on the next server tick.
 *
 * @param delay the ticks to wait before running the task, default is 0
 * @param block action to run
 * @return a BukkitTask that contains the id number
 */
fun runOnServerThread(delay: Long = 0L, block: () -> Unit): BukkitTask = Bukkit.getScheduler()
		.let {
			if (delay != 0L) it.runTask(plugin, block)
			else it.runTaskLater(plugin, block, delay)
		}
