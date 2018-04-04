package cn.berberman.emerald.nms.wrapper.packet

import cn.berberman.emerald.nms.wrapper.player.NmsEntityPlayer
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.nms.wrapper.ReflectionWrapper

/**
 * Corresponding PlayerConnection
 * All methods are realized by reflection.
 * Get from EntityPlayer.
 * @see NmsEntityPlayer
 * @author berberman
 * @param nmsPlayerConnection raw nms player connection
 */
class NmsPlayerConnection(nmsPlayerConnection: Any) : ReflectionWrapper() {
	override val clazz: Class<*> = ReflectionClasses.NmsClass.PlayerConnection()

	override val handle: Any = nmsPlayerConnection

	/**
	 * Send packet to player
	 * @param packet packet to send
	 */
	fun sendPacket(packet: NmsPacket) {
		methods("sendPacket", packet.nmsPacket)
	}
}