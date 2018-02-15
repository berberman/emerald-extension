package cn.berberman.emerald.coroutine

import cn.berberman.emerald.extension.plugin
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.Runnable
import org.bukkit.Bukkit
import kotlin.coroutines.experimental.CoroutineContext

object SpigotCoroutineDispatcher : CoroutineDispatcher() {
	private val bukkitScheduler = Bukkit.getScheduler()
	override fun dispatch(context: CoroutineContext, block: Runnable) {
		bukkitScheduler.runTaskAsynchronously(plugin, block)
	}
}