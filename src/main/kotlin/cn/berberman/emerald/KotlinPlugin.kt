package cn.berberman.emerald

import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

abstract class KotlinPlugin(private val debug: Boolean = false) : JavaPlugin() {


	abstract fun enable()

	abstract fun disable()


	final override fun onEnable() {
		Emerald.registerPlugin(this)
		Emerald.setDebug(debug)
		Emerald.registerInternalEvents()
		enable()
	}

	override fun onDisable() {
		disable()
		HandlerList.unregisterAll(this)
	}


}