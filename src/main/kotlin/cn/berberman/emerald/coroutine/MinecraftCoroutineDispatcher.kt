package cn.berberman.emerald.coroutine

import cn.berberman.emerald.util.EmeraldUtil.plugin
import kotlinx.coroutines.experimental.CoroutineDispatcher
import kotlinx.coroutines.experimental.Runnable
import org.bukkit.Bukkit
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Spigot Coroutine Dispatcher
 *
 * dispatch the coroutine to bukkit scheduler
 * @author berberman
 * @see CoroutineDispatcher
 */
object MinecraftCoroutineDispatcher : CoroutineDispatcher() {

	private val bukkitScheduler = Bukkit.getScheduler()

	override fun dispatch(context: CoroutineContext, block: Runnable) {
		context + BukkitTaskCoroutineContext(bukkitScheduler.runTask(plugin, block))
	}
}