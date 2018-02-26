package cn.berberman.emerald

import cn.berberman.emerald.events.proxy.EmeraldProxySelector
import org.bukkit.plugin.java.JavaPlugin
import java.net.ProxySelector

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
	fun register(plugin: JavaPlugin) {
		Emerald.plugin = plugin
		EmeraldProxySelector.init(ProxySelector.getDefault())
	}

	/**
	 * Set whether print log of registration, command execute, NBT add. Default is false.
	 */
	fun setDebug(boolean: Boolean) {
		debug = boolean
	}
}
