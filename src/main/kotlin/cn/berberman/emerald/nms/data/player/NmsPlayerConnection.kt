package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NmsReflection
import cn.berberman.emerald.nms.NmsUtil
import cn.berberman.emerald.nms.data.chat.interfaces.NmsPacket

/**
 * Corresponding PlayerConnection
 * All methods are realized by reflection.
 * Get from EntityPlayer.
 * @see NmsEntityPlayer
 * @author berberman
 * @param nmsPlayerConnection raw nms player connection
 */
class NmsPlayerConnection(val nmsPlayerConnection: Any) : NmsReflection() {
	override val targetNMSClass: Class<*> = NmsUtil.getNMSClass("PlayerConnection")

	override val instanceNMS: Any
		get() = nmsPlayerConnection

	/**
	 * Send packet to player
	 * @param packet packet to send
	 */
	fun sendPacket(packet: NmsPacket) {
		methods("sendPacket", packet.nmsPacket)
	}
}