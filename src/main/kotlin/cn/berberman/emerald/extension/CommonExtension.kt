package cn.berberman.emerald.extension

import cn.berberman.emerald.dsl.command.CommandHolder
import cn.berberman.emerald.dsl.command.DSLCommandScope
import cn.berberman.emerald.dsl.command.buildCommands
import cn.berberman.emerald.dsl.permission.DSLPermissionScope
import cn.berberman.emerald.dsl.permission.PermissionHolder
import cn.berberman.emerald.util.EmeraldUtil
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity

//internal fun getCommandMap(): CommandMap =
//		Bukkit.getServer().let {
//			it::class.java.declaredMethods.firstOrNull { it.name == "getCommandMap" }
//					?.invoke(it) as CommandMap
//		}

/**
 * Join Chat Color and a String
 * @param s a string be joined to
 * @return result
 */
operator fun ChatColor.times(s: String) =
		toString() + s

/**
 * Spawn a entity.
 * @param T entity type's class
 */
inline fun <reified T : Entity> World.spawnEntity(location: Location) =
		spawn(location, T::class.java) as T


/**
 * Represents the axis of three dimension.
 * @author berberman
 */
enum class Axis3D {
	/**
	 * X direction
	 */
	X,
	/**
	 * Y direction
	 */
	Y,
	/**
	 * Z direction
	 */
	Z
}

/**
 * Infix function to extend CommandSender.sendMessage
 * @param message message to be displayed
 */
infix fun CommandSender.sendMessage(message: String) = sendMessage(message)

/**
 * Log a INFO message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the INFO message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the registered output
 * Handler objects.
 * <p>
 * @param  msg   The string message (or a key in the message catalog)
 */
fun info(msg: String) = EmeraldUtil.logger.info(msg)

/**
 * Log a INFO message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the INFO message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the registered output
 * Handler objects.
 * <p>
 * @param  supplier   A function, which when called, produces the
 *                        desired log message
 */
inline fun info(supplier: () -> String) = EmeraldUtil.logger.info(supplier())

/**
 * Log a WARNING message.
 * <p>
 * If the logger is currently enabled for the WARNING message
 * level then the given message is forwarded to all the
 * registered output Handler objects.
 * <p>
 * @param   msg     The string message (or a key in the message catalog)
 */
fun warning(msg: String) = EmeraldUtil.logger.warning(msg)

/**
 * Log a WARNING message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the WARNING message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the registered output
 * Handler objects.
 * <p>
 * @param   supplier   A function, which when called, produces the
 *                        desired log message
 */
inline fun warning(supplier: () -> String) = EmeraldUtil.logger.warning(supplier())


/**
 * Set a block type
 * @receiver the world that block locate in
 * @param location block location
 * @param type type to set
 * @return this block
 */
fun World.setBlock(location: Location, type: Material): Block =
		getBlockAt(location).apply { this.type = type }

@DslMarker
internal annotation class CommonBuilder


/**
 * Register commands.
 * @param block DSL part of building commands.
 */
fun registerCommands(block: DSLCommandScope.() -> Unit) {
	buildCommands(block)
	CommandHolder.register(EmeraldUtil.commandMap)
}

/**
 * Register permissions.
 * @param block  DSL part of building permissions.
 */
fun registerPermissions(block: DSLPermissionScope.() -> Unit) {
	DSLPermissionScope().block()
	PermissionHolder.register()
}