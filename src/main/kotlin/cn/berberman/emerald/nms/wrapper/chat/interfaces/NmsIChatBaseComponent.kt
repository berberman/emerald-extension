package cn.berberman.emerald.nms.wrapper.chat.interfaces

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper

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

	final override val handle: Any
		get() = nmsChat
}