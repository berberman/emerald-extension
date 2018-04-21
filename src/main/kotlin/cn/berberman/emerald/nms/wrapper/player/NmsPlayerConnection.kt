package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.extension.unsafeCast
import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.nms.wrapper.packet.NmsPacket
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.getField
import cn.berberman.emerald.util.NmsUtil
import io.netty.channel.Channel

/**
 * Corresponding PlayerConnection
 * All methods are realized by reflection.
 * Get from EntityPlayer.
 * @see NmsEntityPlayer
 * @author berberman
 * @param nmsPlayerConnection raw nms player connection
 */
class NmsPlayerConnection(nmsPlayerConnection: Any) : ReflectionWrapper() {
	override val clazz: Class<*> = ReflectionClasses.Nms.PlayerConnection()

	override val handle: Any = nmsPlayerConnection

	val channel: Channel = fields("networkManager").let {
		NmsUtil.getNMSClass("NetworkManager").getField(it!!, "channel")!!.unsafeCast()
	}

	/**
	 * Send packet to player
	 * @param packet packet to send
	 */
	fun sendPacket(packet: NmsPacket) {
		methods("sendPacket", packet.nmsPacket)
	}
}