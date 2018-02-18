package cn.berberman.emerald.nms.data.player.interfaces

import cn.berberman.emerald.nms.NMSAReflection

/**
 * Corresponding Packet, a nms interface
 * All methods are realized by reflection.
 * @author berberman
 */
abstract class NMSPacket : NMSAReflection() {
	/**
	 * an instance of nms packet, should be inherited
	 */
	abstract val nmsPacket: Any

	override val instanceNMS: Any
		get() = nmsPacket
}