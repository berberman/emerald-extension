package cn.berberman.emerald.nms.data.player.interfaces

import cn.berberman.emerald.nms.NMSReflection

/**
 * Corresponding Packet, a nms interface
 * All methods are realized by reflection.
 * @author berberman
 */
abstract class NMSPacket : NMSReflection() {
	/**
	 * an instance of nms packet, should be inherited
	 */
	abstract val nmsPacket: Any
}