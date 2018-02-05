package cn.berberman.emerald

import cn.berberman.emerald.dsl.command.CommandHolder
import cn.berberman.emerald.dsl.command.DSLCommandScope
import cn.berberman.emerald.dsl.command.buildCommands
import cn.berberman.emerald.dsl.event.DSLEventScope
import cn.berberman.emerald.dsl.event.EventHolder
import cn.berberman.emerald.dsl.event.buildEvents
import cn.berberman.emerald.dsl.permission.DSLPermissionScope
import cn.berberman.emerald.dsl.permission.PermissionHolder
import cn.berberman.emerald.extension.getCommandMap
import org.bukkit.plugin.java.JavaPlugin

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

	/**
	 * Register commands.
	 * @param block DSL part of building commands.
	 */
	fun registerCommands(block: DSLCommandScope.() -> Unit) {
		buildCommands(block)
		CommandHolder.register(getCommandMap())
	}

	/**
	 * Register events.
	 * @param block DSL part of building event listeners.
	 */
	fun registerEvents(block: DSLEventScope.() -> Unit) {
		buildEvents(block)
		EventHolder.register()
	}

	/**
	 * Register permissions.
	 * @param block  DSL part of building permissions.
	 */
	fun registerPermissions(block: DSLPermissionScope.() -> Unit) {
		DSLPermissionScope().block()
		PermissionHolder.register()
	}
}