package cn.berberman.emerald

import cn.berberman.emerald.dsl.event.register
import cn.berberman.emerald.util.ChannelHandler
import cn.berberman.emerald.util.EmeraldProxySelector
import cn.berberman.emerald.util.onlinemode.OnlineModeValidate
import org.bukkit.Bukkit
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
	fun registerPlugin(plugin: JavaPlugin) {
		Emerald.plugin = plugin
	}

	/**
	 * Set whether print log of registration, command execute, NBT add. Default is false.
	 */
	fun setDebug(boolean: Boolean) {
		debug = boolean
	}

	fun registerInternalEvents() {
		EmeraldProxySelector.init(ProxySelector.getDefault())
		if (Bukkit.getOnlineMode())
			OnlineModeValidate.loginEvent.register()
		ChannelHandler.joinInject.register()
		ChannelHandler.quitInject.register()
	}
}
