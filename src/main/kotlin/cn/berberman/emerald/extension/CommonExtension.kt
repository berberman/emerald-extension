package cn.berberman.emerald.extension

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.event.Listener
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

fun getCommandMap(): CommandMap =
		Bukkit.getServer().let {
			it::class.java.declaredMethods.firstOrNull { it.name == "getCommandMap" }
					?.invoke(it) as CommandMap
		}

operator fun ChatColor.times(s: String) =
		toString() + s

inline fun <reified T : Entity> World.spawnEntity(location: Location) =
		spawn(location, T::class.java) as T

inline fun <reified T : JavaPlugin> getPluginBean(): T = JavaPlugin.getPlugin(T::class.java)

val plugin = Emerald.plugin

val logger: Logger = plugin.logger

val pluginManager: PluginManager = Bukkit.getPluginManager()

enum class Axis3D { X, Y, Z }


infix fun CommandSender.sendMessage(message: String) = sendMessage(message)

val emptyListener = object : Listener {}

