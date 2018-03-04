package cn.berberman.emerald.nms.wrapper.chat.interfaces

import cn.berberman.emerald.reflection.ReflectionWrapper

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

	override val instance: Any
		get() = nmsPacket
}