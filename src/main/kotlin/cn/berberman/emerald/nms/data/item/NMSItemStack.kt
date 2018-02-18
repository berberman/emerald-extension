package cn.berberman.emerald.nms.data.item

import cn.berberman.emerald.nms.NMSAReflection
import cn.berberman.emerald.nms.NMSAUtil
import cn.berberman.emerald.nms.data.nbt.NMSNBTTagCompound
import org.bukkit.inventory.ItemStack

/**
 * Corresponding ItemStack
 * All methods are realized by reflection.
 * @author berberman
 * @param itemStack a bukkit itemStack instance
 */
class NMSItemStack(itemStack: ItemStack) : NMSAReflection() {
	/**
	 * internal property to save corresponding nms class.
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val targetNMSClass: Class<*> = NMSAUtil.getNMSClass("ItemStack")
	/**
	 * an instance of nmsItemStack holds by this class.
	 */
	override val instanceNMS: Any = NMSAUtil.asNMSCopy(itemStack)

	/**
	 * Whether nms has NBT Tag.
	 * @return `true` if it has.
	 */
	fun hasTag() = methods("hasTag") as Boolean

	/**
	 * Get NBT Tag from nms
	 * @return nms NBT Tag.
	 */
	fun getTag() = NMSNBTTagCompound(methods("getTag")!!)

	/**
	 * Set nms NBT Tag
	 * @param nmsNBTTagCompound tag
	 */
	fun setTag(nmsNBTTagCompound: NMSNBTTagCompound) {
		methods("setTag", nmsNBTTagCompound.instanceNMS)
	}
}
