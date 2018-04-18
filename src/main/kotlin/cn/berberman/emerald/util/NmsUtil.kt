package cn.berberman.emerald.util

import cn.berberman.emerald.extension.safeCast
import cn.berberman.emerald.extension.unsafeCast
import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.invokeMethodSpecificTypes
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.IllegalPluginAccessException

/**
 * A utilities to easy operate nms.
 */
object NmsUtil {
	val version = Bukkit.getServer()::class.java.`package`.name.split(".")[3]
	private val nmsPackageName = "net.minecraft.server.$version"
	private val craftBukkitPackageName = "org.bukkit.craftbukkit.$version"

	/**
	 * Get NMS Class.
	 * @param name class name
	 */
	fun getNMSClass(name: String): Class<*> = Class.forName("$nmsPackageName.$name")
			?: throw IllegalPluginAccessException("Can't find this class: $name")

	/**
	 * Get Craft Bukkit Class
	 * @param nameWithPackage package name and class name
	 */
	fun getCraftBukkitClass(nameWithPackage: String): Class<*> = Class.forName("$craftBukkitPackageName.$nameWithPackage")
			?: throw IllegalPluginAccessException("Can't find this class: $nameWithPackage")

	/**
	 * Convert bukkit ItemStack to NmsItemStack
	 * @param original bukkit ItemStack
	 */
	fun asNMSCopy(original: ItemStack): Any = ReflectionClasses.CraftBukkit.CraftItemStack()
			.invokeMethodSpecificTypes(null, "asNMSCopy", arrayOf(ItemStack::class.java.unsafeCast()), original)
			?: throw IllegalPluginAccessException("Convert $original error.")

	/**
	 * Convert NMS ItemStack to bukkit ItemStack
	 * @param original NMS ItemStack
	 */
	fun asBukkitCopy(original: NmsItemStack): ItemStack = ReflectionClasses.CraftBukkit.CraftItemStack()
			.invokeMethodSpecificTypes(null, "asBukkitCopy", arrayOf(ReflectionClasses.Nms.ItemStack()), original.handle)
			.safeCast() ?: throw IllegalPluginAccessException("Convert $original error.")
}