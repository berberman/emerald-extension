package cn.berberman.emerald.nms.wrapper.item

import cn.berberman.emerald.nms.wrapper.nbt.NmsNBTTagCompound
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.util.NmsUtil
import org.bukkit.inventory.ItemStack

/**
 * Corresponding ItemStack
 * All methods are realized by reflection.
 * @author berberman
 * @param itemStack a bukkit itemStack instance
 */
class NmsItemStack(itemStack: ItemStack) : ReflectionWrapper() {
	/**
	 * internal property to save corresponding nms class.
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val clazz: Class<*> = ReflectionClasses.NmsClass.ItemStack()
	/**
	 * an instance of nmsItemStack holds by this class.
	 */
	override val handle: Any = NmsUtil.asNMSCopy(itemStack)

	/**
	 * Whether nms has NBT Tag.
	 * @return `true` if it has.
	 */
	fun hasTag() = methods("hasTag") as Boolean

	/**
	 * Get NBT Tag from nms
	 * @return nms NBT Tag.
	 */
	fun getTag() = NmsNBTTagCompound(methods("getTag")!!)

	/**
	 * Set nms NBT Tag
	 * @param nmsNBTTagCompound tag
	 */
	fun setTag(nmsNBTTagCompound: NmsNBTTagCompound) {
		methods("setTag", nmsNBTTagCompound.handle)
	}
}
