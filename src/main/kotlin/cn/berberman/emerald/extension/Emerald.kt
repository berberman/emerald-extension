package cn.berberman.emerald.extension

import cn.berberman.emerald.extension.dsl.command.CommandHolder
import cn.berberman.emerald.extension.dsl.command.DSLCommandScope
import cn.berberman.emerald.extension.dsl.command.buildCommands
import cn.berberman.emerald.extension.dsl.event.DSLEventScope
import cn.berberman.emerald.extension.dsl.event.EventHolder
import cn.berberman.emerald.extension.dsl.event.buildEvents
import cn.berberman.emerald.extension.dsl.permission.DSLPermissionScope
import cn.berberman.emerald.extension.dsl.permission.PermissionHolder
import cn.berberman.emerald.extension.extension.getCommandMap
import org.bukkit.plugin.java.JavaPlugin

object Emerald {
	internal lateinit var plugin: JavaPlugin

	internal var debug = false

	fun registerContext(plugin: JavaPlugin) {
		this.plugin = plugin
	}

	fun setDebug(boolean: Boolean) {
		debug = boolean
	}

	fun registerCommands(block: DSLCommandScope.() -> Unit) {
		buildCommands(block)
		CommandHolder.register(getCommandMap())
	}

	fun registerEvents(block: DSLEventScope.() -> Unit) {
		buildEvents(block)
		EventHolder.register()
	}

	fun registerPermissions(block: DSLPermissionScope.() -> Unit) {
		DSLPermissionScope().block()
		PermissionHolder.register()
	}
}