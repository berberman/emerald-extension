package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.extension.getFieldAccess
import cn.berberman.emerald.nms.NmsReflection
import cn.berberman.emerald.util.NmsUtil

/**
 * Corresponding EntityPlayer
 * All methods are realized by reflection.
 * Get from craft player.
 * @author berberman
 * @param nmsEntityPlayer nmsEntityPlayer, get from reflection [BukkitCraftPlayer]
 */
class NmsEntityPlayer(val nmsEntityPlayer: Any) : NmsReflection() {
	override val targetNMSClass: Class<*> = NmsUtil.getNMSClass("EntityPlayer")

	override val instanceNMS: Any
		get() = nmsEntityPlayer
	/**
	 * nms player connection instance
	 */
	val playerConnection = NmsPlayerConnection(targetNMSClass.getFieldAccess()[nmsEntityPlayer, "playerConnection"])

}