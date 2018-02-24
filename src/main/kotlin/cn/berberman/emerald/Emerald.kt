package cn.berberman.emerald

import org.bukkit.plugin.java.JavaPlugin

/**
 * Main class of this extension.
 * @author berberman
 */
object Emerald {
	internal lateinit var plugin: JavaPlugin

	internal var debug = false

	/**
	 * Quote plugin instance, must be call if you want to use this API.
	 */
	fun registerContext(plugin: JavaPlugin) {
		Emerald.plugin = plugin
	}

	/**
	 * Set whether print log of registration, command execute, NBT add. Default is false.
	 */
	fun setDebug(boolean: Boolean) {
		debug = boolean
	}
}
