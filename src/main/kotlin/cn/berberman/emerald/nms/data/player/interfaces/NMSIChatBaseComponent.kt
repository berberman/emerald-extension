package cn.berberman.emerald.nms.data.player.interfaces

import cn.berberman.emerald.nms.NMSAReflection

/**
 * Corresponding IChatBaseComponent, a nms interface
 * All methods are realized by reflection.
 * @author berberman
 */
abstract class NMSIChatBaseComponent protected constructor() : NMSAReflection() {
	/**
	 * an instance of nms chat, should be inherited
	 */
	abstract val nmsChat: Any

	override val instanceNMS: Any
		get() = nmsChat
}