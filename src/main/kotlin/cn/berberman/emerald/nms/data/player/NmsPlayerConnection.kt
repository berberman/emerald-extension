package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.reflection.ReflectionWrapper
import cn.berberman.emerald.nms.data.chat.interfaces.NmsPacket
import cn.berberman.emerald.util.NmsUtil

/**
 * Corresponding PlayerConnection
 * All methods are realized by reflection.
 * Get from EntityPlayer.
 * @see NmsEntityPlayer
 * @author berberman
 * @param nmsPlayerConnection raw nms player connection
 */
class NmsPlayerConnection(val nmsPlayerConnection: Any) : ReflectionWrapper() {
	override val clazz: Class<*> = NmsUtil.getNMSClass("PlayerConnection")

	override val instance: Any
		get() = nmsPlayerConnection

	/**
	 * Send packet to player
	 * @param packet packet to send
	 */
	fun sendPacket(packet: NmsPacket) {
		methods("sendPacket", packet.nmsPacket)
	}
}