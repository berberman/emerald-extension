package cn.berberman.emerald.extension.dsl.nms.item

import cn.berberman.emerald.extension.dsl.nms.item.data.NMSItemStack
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack

object NMSUtil {
	private val version = Bukkit.getServer()::class.java.`package`.name.split(".")[3]
	private val nmsPackageName = "net.minecraft.server.$version"
	private val craftBukkitPackageName = "org.bukkit.craftbukkit.$version"

	/**
	 * Get NMS Class.
	 * @param name class name
	 */
	fun getNMSClass(name: String): Class<*> = Class.forName("$nmsPackageName.$name")

	/**
	 * Get Craft Bukkit Class
	 * @param nameWithPackage package name and class name
	 */
	fun getCraftBukkitClass(nameWithPackage: String): Class<*> = Class.forName("$craftBukkitPackageName.$nameWithPackage")

	/**
	 * Convert bukkit ItemStack to NMSItemStack
	 * @param original bukkit ItemStack
	 */
	fun asNMSCopy(original: ItemStack): Any = getCraftBukkitClass("inventory.CraftItemStack")
			.getMethod("asNMSCopy", ItemStack::class.java)(original, original)

	/**
	 * Convert NMS ItemStack to bukkit ItemStack
	 * @param original NMS ItemStack
	 */
	fun asBukkitCopy(original: NMSItemStack) = getCraftBukkitClass("inventory.CraftItemStack")
			.methods.firstOrNull { it.name == "asBukkitCopy" }!!(original.nmsItemStack, original.nmsItemStack) as ItemStack
}