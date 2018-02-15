package cn.berberman.emerald.extension

import cn.berberman.emerald.Emerald
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

internal fun getCommandMap(): CommandMap =
		Bukkit.getServer().let {
			it::class.java.declaredMethods.firstOrNull { it.name == "getCommandMap" }
					?.invoke(it) as CommandMap
		}

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
 * Get one plugin's instance.
 * @param T main class of that plugin
 */
inline fun <reified T : JavaPlugin> getPluginBean(): T = JavaPlugin.getPlugin(T::class.java)

/**
 * Quoted plugin instance.
 */
val plugin = Emerald.plugin
/**
 * Plugin's logger.
 */
val logger: Logger = plugin.logger
/**
 * Plugin manager.
 */
val pluginManager: PluginManager = Bukkit.getPluginManager()

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

internal val emptyListener = object : Listener {}
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
fun info(msg: String) = logger.info(msg)

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
inline fun info(supplier: () -> String) = logger.info(supplier())

/**
 * Log a WARNING message.
 * <p>
 * If the logger is currently enabled for the WARNING message
 * level then the given message is forwarded to all the
 * registered output Handler objects.
 * <p>
 * @param   msg     The string message (or a key in the message catalog)
 */
fun warning(msg: String) = logger.warning(msg)

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
inline fun warning(supplier: () -> String) = logger.warning(supplier())

/**
 * Creates an empty inventory.
 *
 * @param holder the holder of the inventory, default is null which indicate no holder
 * @param lines a multiple of 9 as the lines of inventory to create
 * @param title the title of the inventory, displayed when inventory is
 *     viewed
 * @param type  If the type is CHEST, the new inventory has a size of 27; otherwise the
 *     new inventory has the normal size for its type. Default is null.
 * @return a new inventory
 * @throws IllegalArgumentException if the size is not a multiple of 9
 */
fun createInventory(holder: InventoryHolder? = null, lines: Int? = null,
                    type: InventoryType? = null, title: String): Inventory =
		title.takeIf { it.isNotBlank() }.let { name ->
			if (lines == null && name == null && type != null)
				Bukkit.createInventory(holder, type)
			else if (lines != null && name == null && type == null)
				Bukkit.createInventory(holder, lines * 9)
			else if (lines != null && name != null && type == null)
				Bukkit.createInventory(holder, lines * 9, name)
			else if (lines == null && name != null && type != null)
				Bukkit.createInventory(holder, type, name)
			else throw IllegalArgumentException()
		}

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