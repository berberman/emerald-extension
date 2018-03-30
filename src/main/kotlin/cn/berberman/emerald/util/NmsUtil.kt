package cn.berberman.emerald.util

import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.invokeMethod
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
	fun asNMSCopy(original: ItemStack): Any = ReflectionClasses.CraftBukkitClass.CraftItemStack()
			.invokeMethod(null, "asNMSCopy", arrayOf(ItemStack::class.java), original)
			?: throw IllegalPluginAccessException("Convert $original error.")
//	fun asNMSCopy(original: ItemStack): Any = ReflectionClasses.CraftBukkitClass.CraftItemStack()
//			.getMethod("asNMSCopy", ItemStack::class.java)?.invoke(original, original)
//			?: throw IllegalPluginAccessException("Convert $original error.")


	/**
	 * Convert NMS ItemStack to bukkit ItemStack
	 * @param original NMS ItemStack
	 */
	fun asBukkitCopy(original: NmsItemStack) = ReflectionClasses.CraftBukkitClass.CraftItemStack()
			.invokeMethod(null, "asBukkitCopy", arrayOf(ReflectionClasses.NmsClass.ItemStack()), original.instance) as? ItemStack
			?: throw IllegalPluginAccessException("Convert $original error.")
//	fun asBukkitCopy(original: NmsItemStack) = ReflectionClasses.CraftBukkitClass.CraftItemStack()
//			.methods.firstOrNull { it.name == "asBukkitCopy" }?.invoke(original.instance, original.instance) as? ItemStack
//			?: throw IllegalPluginAccessException("Convert $original error.")
}