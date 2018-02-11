package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSReflection
import cn.berberman.emerald.nms.NMSUtil
import java.lang.reflect.Method

/**
 * Corresponding EntityPlayer
 * All methods are realized by reflection.
 * Get from craft player.
 * @author berberman
 * @param nmsEntityPlayer nmsEntityPlayer, get from reflection [NMSCraftPlayer]
 */
class NMSEntityPlayer(val nmsEntityPlayer: Any) : NMSReflection() {
	override val targetNMSClass: Class<*> = NMSUtil.getNMSClass("EntityPlayer")

	override val methods: HashMap<String, Method> = hashMapOf()

	/**
	 * nms player connection instance
	 */
	val playerConnection = NMSPlayerConnection(targetNMSClass.getField("playerConnection")[nmsEntityPlayer])

}