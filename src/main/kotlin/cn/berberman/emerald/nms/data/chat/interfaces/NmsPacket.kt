package cn.berberman.emerald.nms.data.chat.interfaces

import cn.berberman.emerald.nms.NmsReflection

/**
 * Corresponding Packet, a nms interface
 * All methods are realized by reflection.
 * @author berberman
 */
abstract class NmsPacket : NmsReflection() {
	/**
	 * an instance of nms packet, should be inherited
	 */
	abstract val nmsPacket: Any

	override val instanceNMS: Any
		get() = nmsPacket
}