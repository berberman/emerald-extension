package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSAReflection
import cn.berberman.emerald.nms.NMSAUtil
import cn.berberman.emerald.nms.data.player.interfaces.NMSPacket

/**
 * Corresponding PlayerConnection
 * All methods are realized by reflection.
 * Get from EntityPlayer.
 * @see NMSEntityPlayer
 * @author berberman
 * @param nmsPlayerConnection raw nms player connection
 */
class NMSPlayerConnection(val nmsPlayerConnection: Any) : NMSAReflection() {
	override val targetNMSClass: Class<*> = NMSAUtil.getNMSClass("PlayerConnection")

	override val instanceNMS: Any
		get() = nmsPlayerConnection

	/**
	 * Send packet to player
	 * @param packet packet to send
	 */
	fun sendPacket(packet: NMSPacket) {
		methods("sendPacket", packet.nmsPacket)
	}
}