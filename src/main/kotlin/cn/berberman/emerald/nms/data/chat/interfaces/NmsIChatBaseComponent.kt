package cn.berberman.emerald.nms.data.chat.interfaces

import cn.berberman.emerald.reflection.ReflectionWrapper

/**
 * Corresponding IChatBaseComponent, a nms interface
 * All methods are realized by reflection.
 * @author berberman
 */
abstract class NmsIChatBaseComponent protected constructor() : ReflectionWrapper() {
	/**
	 * an instance of nms chat, should be inherited
	 */
	abstract val nmsChat: Any

	override val instance: Any
		get() = nmsChat
}