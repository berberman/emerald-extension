package cn.berberman.emerald.nmsItem.data

import cn.berberman.emerald.extension.invoke
import cn.berberman.emerald.nmsItem.NMSReflection
import cn.berberman.emerald.nmsItem.NMSUtil
import org.bukkit.inventory.ItemStack
import java.lang.reflect.Method

/**
 * Corresponding ItemStack
 * All methods are realized by reflection.
 * @author berberman
 * @param itemStack a bukkit itemStack instance
 */
class NMSItemStack(itemStack: ItemStack) : NMSReflection() {
	/**
	 * internal property to save corresponding nms class.
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val targetNMSClass: Class<*> = NMSUtil.getNMSClass("ItemStack")
	/**
	 * an instance of nmsItemStack holds by this class.
	 */
	val nmsItemStack: Any = NMSUtil.asNMSCopy(itemStack)
	/**
	 * internal property to save all methods.
	 *  You can't access this property, because it's inherited from NMSReflection.
	 */
	override val rawMethods: Array<out Method> = targetNMSClass.methods

	/**
	 * internal function to get methods instance.
	 *     You can't access this method, because it's inherited from NMSReflection.
	 */
	override fun getMethod(name: String) = rawMethods.first { it.name == name }

	/**
	 * internal property to save realized methods.
	 *  You can't access this property, because it's inherited from NMSReflection.
	 */
	override val methods = hashMapOf(
			"hasTag" to getMethod("hasTag"),
			"getTag" to getMethod("getTag"),
			"setTag" to getMethod("setTag")
	)

	/**
	 * Whether nmsItem has NBT Tag.
	 * @return `true` if it has.
	 */
	fun hasTag() = methods("hasTag")(nmsItemStack) as Boolean

	/**
	 * Get NBT Tag from nmsItem
	 * @return nmsItem's NBT Tag.
	 */
	fun getTag() = NMSNBTTagCompound(methods("getTag")(nmsItemStack))

	/**
	 * Set nmsItem's NBT Tag
	 * @param nmsNBTTagCompound tag
	 */
	fun setTag(nmsNBTTagCompound: NMSNBTTagCompound) {
		methods("setTag")(nmsItemStack, nmsNBTTagCompound.tagCompound)
	}
}
