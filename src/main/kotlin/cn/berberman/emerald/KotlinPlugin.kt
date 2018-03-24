package cn.berberman.emerald

import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

abstract class KotlinPlugin(debug: Boolean = false) : JavaPlugin() {
	init {
		register()
		Emerald.setDebug(debug)
	}

	private fun register() = Emerald.register(this)

	protected fun unregisterListeners() =
			HandlerList.unregisterAll(this)

}