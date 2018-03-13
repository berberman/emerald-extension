package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.reflection.ReflectionWrapper
import cn.berberman.emerald.reflection.getFieldAccess
import cn.berberman.emerald.util.NmsUtil

/**
 * Corresponding EntityPlayer
 * All methods are realized by reflection.
 * Get from craft player.
 * @author berberman
 * @param nmsEntityPlayer nmsEntityPlayer, get from reflection [BukkitCraftPlayer]
 */
class NmsEntityPlayer(val nmsEntityPlayer: Any) : ReflectionWrapper() {
	override val clazz: Class<*> = NmsUtil.getNMSClass("EntityPlayer")

	override val instance: Any
		get() = nmsEntityPlayer
	/**
	 * nms player connection instance
	 */
	val playerConnection = NmsPlayerConnection(clazz.getFieldAccess()[nmsEntityPlayer, "playerConnection"])

}