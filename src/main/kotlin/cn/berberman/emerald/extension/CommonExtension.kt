package cn.berberman.emerald.extension

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
 * Set a block type
 * @receiver the world that block locate in
 * @param location block location
 * @param type type to set
 * @return this block
 */
fun World.setBlock(location: Location, type: Material): Block =
		getBlockAt(location).apply { this.type = type }

