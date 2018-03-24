package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.nms.wrapper.packet.NmsPlayerConnection
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.ReflectionWrapper
import cn.berberman.emerald.reflection.getFieldAccess
import cn.berberman.emerald.reflection.invokeMethod
import org.bukkit.inventory.ItemStack

/**
 * Corresponding EntityPlayer
 * All methods are realized by reflection.
 * Get from craft player.
 * @author berberman
 * @param nmsEntityPlayer nmsEntityPlayer, get from reflection [BukkitCraftPlayer]
 */
class NmsEntityPlayer(val nmsEntityPlayer: Any) : ReflectionWrapper() {
	override val clazz: Class<*> = ReflectionClasses.NmsClass.EntityPlayer()

	override val instance: Any
		get() = nmsEntityPlayer
	/**
	 * nms player connection instance
	 */
	val playerConnection = NmsPlayerConnection(clazz.getFieldAccess()[nmsEntityPlayer, "playerConnection"])

	fun openBook(itemStack: ItemStack, enumHand: NmsEnumHand) {
		clazz.invokeMethod(instance, "a", arrayOf(ReflectionClasses.NmsClass.ItemStack(),
				ReflectionClasses.NmsClass.EnumHand()), NmsItemStack(itemStack).instance, enumHand.getNmsInstance())
	}

	fun broadcastCarriedItem() {
		methods("broadcastCarriedItem")
	}
}