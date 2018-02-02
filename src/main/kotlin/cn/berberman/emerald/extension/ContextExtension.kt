package cn.berberman.emerald.extension

import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

fun asyncLoop(delay: Long = 0L, block: () -> Boolean) = async(delay) {
	var result = block()
	while (result)
		result = block()
}


fun async(delay: Long = 0L, block: () -> Unit): BukkitTask = Bukkit.getScheduler()
		.let {
			if (delay != 0L) it.runTaskAsynchronously(plugin, block)
			else it.runTaskLaterAsynchronously(plugin, block, delay)
		}

fun runOnServerThread(delay: Long = 0L, block: () -> Unit): BukkitTask = Bukkit.getScheduler()
		.let {
			if (delay != 0L) it.runTask(plugin, block)
			else it.runTaskLater(plugin, block, delay)
		}