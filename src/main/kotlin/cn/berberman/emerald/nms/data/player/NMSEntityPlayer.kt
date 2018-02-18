package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.extension.getFieldAccess
import cn.berberman.emerald.nms.NMSAReflection
import cn.berberman.emerald.nms.NMSAUtil

/**
 * Corresponding EntityPlayer
 * All methods are realized by reflection.
 * Get from craft player.
 * @author berberman
 * @param nmsEntityPlayer nmsEntityPlayer, get from reflection [NMSCraftPlayer]
 */
class NMSEntityPlayer(val nmsEntityPlayer: Any) : NMSAReflection() {
	override val targetNMSClass: Class<*> = NMSAUtil.getNMSClass("EntityPlayer")

	override val instanceNMS: Any
		get() = nmsEntityPlayer
	/**
	 * nms player connection instance
	 */
	val playerConnection = NMSPlayerConnection(targetNMSClass.getFieldAccess()[nmsEntityPlayer, "playerConnection"])

}