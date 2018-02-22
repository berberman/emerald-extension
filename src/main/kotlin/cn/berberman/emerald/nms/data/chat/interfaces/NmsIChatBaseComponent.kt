package cn.berberman.emerald.nms.data.chat.interfaces

import cn.berberman.emerald.nms.NmsReflection

/**
 * Corresponding IChatBaseComponent, a nms interface
 * All methods are realized by reflection.
 * @author berberman
 */
abstract class NmsIChatBaseComponent protected constructor() : NmsReflection() {
	/**
	 * an instance of nms chat, should be inherited
	 */
	abstract val nmsChat: Any

	override val instanceNMS: Any
		get() = nmsChat
}