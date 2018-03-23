package cn.berberman.emerald.nms.wrapper.chat

import cn.berberman.emerald.reflection.ReflectionClasses

/**
 * Corresponding ChatMessageType Enum
 * All methods are realized by reflection.
 * @author berberman
 */
enum class NmsChatMessageType(val type: Byte) {
	/**
	 * normal format
	 */
	CHAT(0),
	/**
	 * system format
	 */
	SYSTEM(1),
	/**
	 * action bar
	 */
	GAME_INFO(2);

	/**
	 * Use reflection to get enum instance.
	 * @return nms instance of the enum.
	 */
	fun getNMSInstance(): Any {
		val clazz = ReflectionClasses.NmsClass.ChatMessageType()
		val a = clazz.methods.first { it.name == "a" && it.returnType == clazz }
		return a(null, type)
	}
}