package cn.berberman.emerald.util

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.reflection.getFieldAccess
import cn.berberman.emerald.reflection.invokeMethod
import javassist.ClassPool
import javassist.LoaderClassPath
import org.bukkit.Bukkit
import org.bukkit.command.CommandMap
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

object EmeraldUtil {

	internal val craftServerClass = NmsUtil.getCraftBukkitClass("CraftServer")

	val commandMap = craftServerClass
			.invokeMethod(Bukkit.getServer(), "getCommandMap") as CommandMap

	val serverThread = NmsUtil.getNMSClass("MinecraftServer").getFieldAccess()[craftServerClass
			.getDeclaredField("console")
			.also { it.isAccessible = true }
			[Bukkit.getServer()], "primaryThread"] as Thread

	internal val emptyListener = object : Listener {}

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

	val pool = ClassPool.getDefault().also { it.insertClassPath(LoaderClassPath(pluginManager.javaClass.classLoader)) }

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

}