package cn.berberman.emerald.nms.wrapper.packet

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper

/**
 * Corresponding Packet, a nms interface
 * All methods are realized by reflection.
 * @author berberman
 */
abstract class NmsPacket : ReflectionWrapper() {
	/**
	 * an instance of nms packet, should be inherited
	 */
	abstract val nmsPacket: Any

	final override val handle: Any
		get() = nmsPacket
}