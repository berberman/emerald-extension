package cn.berberman.emerald.nmsItem

import cn.berberman.emerald.exception.NMSException
import cn.berberman.emerald.nmsItem.data.NMSItemStack
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack

/**
 * A utilities to easy operate nms.
 */
object NMSUtil {
	private val version = Bukkit.getServer()::class.java.`package`.name.split(".")[3]
	private val nmsPackageName = "net.minecraft.server.${version}"
	private val craftBukkitPackageName = "org.bukkit.craftbukkit.${version}"

	/**
	 * Get NMS Class.
	 * @param name class name
	 */
	fun getNMSClass(name: String): Class<*> = Class.forName("${nmsPackageName}.$name")
			?: throw NMSException("Can't find this class: $name")

	/**
	 * Get Craft Bukkit Class
	 * @param nameWithPackage package name and class name
	 */
	fun getCraftBukkitClass(nameWithPackage: String): Class<*> = Class.forName("${craftBukkitPackageName}.$nameWithPackage")
			?: throw NMSException("Can't find this class: $nameWithPackage")

	/**
	 * Convert bukkit ItemStack to NMSItemStack
	 * @param original bukkit ItemStack
	 */
	fun asNMSCopy(original: ItemStack): Any = getCraftBukkitClass("inventory.CraftItemStack")
			.getMethod("asNMSCopy", ItemStack::class.java)?.invoke(original, original)
			?: throw NMSException("Convert $original error.")

	/**
	 * Convert NMS ItemStack to bukkit ItemStack
	 * @param original NMS ItemStack
	 */
	fun asBukkitCopy(original: NMSItemStack) = getCraftBukkitClass("inventory.CraftItemStack")
			.methods.firstOrNull { it.name == "asBukkitCopy" }?.invoke(original.nmsItemStack, original.nmsItemStack) as? ItemStack
			?: throw NMSException("Convert $original error.")
}