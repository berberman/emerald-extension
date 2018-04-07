package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.nms.wrapper.packet.NmsPlayerConnection
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.getFieldAccess
import cn.berberman.emerald.reflection.invokeMethod
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Corresponding EntityPlayer
 * All methods are realized by reflection.
 * Get from craft player.
 * @author berberman
 * @param nmsEntityPlayer nmsEntityPlayer, get from reflection [BukkitCraftPlayer]
 */
class NmsEntityPlayer(nmsEntityPlayer: Any) : ReflectionWrapper() {
	override val clazz: Class<*> = ReflectionClasses.NmsClass.EntityPlayer()

	override val handle: Any = nmsEntityPlayer
	/**
	 * nms player connection instance
	 */
	val playerConnection = NmsPlayerConnection(clazz.getFieldAccess()[nmsEntityPlayer, "playerConnection"])

	fun openBook(enumHand: NmsEnumHand) {
		clazz.invokeMethod(handle, "a", arrayOf(ReflectionClasses.NmsClass.ItemStack(),
				ReflectionClasses.NmsClass.EnumHand()), NmsItemStack(ItemStack(Material.WRITTEN_BOOK)).handle, enumHand.handle)
	}

	fun broadcastCarriedItem() {
		methods("broadcastCarriedItem")
	}
}