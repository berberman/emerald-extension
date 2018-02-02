package cn.berberman.emerald.extension

import org.bukkit.plugin.java.JavaPlugin

object Emerald {
	internal lateinit var plugin: JavaPlugin

	internal var debug = false

	fun register(plugin: JavaPlugin) {
		this.plugin = plugin
	}

	fun setDebug(boolean: Boolean) {
		debug = boolean
	}
}