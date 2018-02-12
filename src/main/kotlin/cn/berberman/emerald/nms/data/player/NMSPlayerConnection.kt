package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSReflection
import cn.berberman.emerald.nms.NMSUtil
import cn.berberman.emerald.nms.data.player.interfaces.NMSPacket
import java.lang.reflect.Method

/**
 * Corresponding PlayerConnection
 * All methods are realized by reflection.
 * Get from EntityPlayer.
 * @see NMSEntityPlayer
 * @author berberman
 * @param nmsPlayerConnection raw nms player connection
 */
class NMSPlayerConnection(val nmsPlayerConnection: Any) : NMSReflection() {
	override val targetNMSClass: Class<*> = NMSUtil.getNMSClass("PlayerConnection")

	override val methods: HashMap<String, Method> = hashMapOf(
			"sendPacket" to getMethod("sendPacket")
	)

	/**
	 * Send packet to player
	 * @param packet packet to send
	 */
	fun sendPacket(packet: NMSPacket) {
		methods("sendPacket")(nmsPlayerConnection, packet.nmsPacket)
	}
}