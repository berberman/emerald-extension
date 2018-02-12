package cn.berberman.emerald

import cn.berberman.emerald.dsl.command.CommandHolder
import cn.berberman.emerald.dsl.command.DSLCommandScope
import cn.berberman.emerald.dsl.command.buildCommands
import cn.berberman.emerald.dsl.permission.DSLPermissionScope
import cn.berberman.emerald.dsl.permission.PermissionHolder
import cn.berberman.emerald.extension.getCommandMap
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

/**
 * Register commands.
 * @param block DSL part of building commands.
 */
fun registerCommands(block: DSLCommandScope.() -> Unit) {
	buildCommands(block)
	CommandHolder.register(getCommandMap())
}

/**
 * Register permissions.
 * @param block  DSL part of building permissions.
 */
fun registerPermissions(block: DSLPermissionScope.() -> Unit) {
	DSLPermissionScope().block()
	PermissionHolder.register()
}