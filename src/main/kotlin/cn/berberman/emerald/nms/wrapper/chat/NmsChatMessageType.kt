package cn.berberman.emerald.nms.wrapper.chat

import cn.berberman.emerald.nms.wrapper.IWrapper
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.invokeMethodSpecificTypes

/**
 * Corresponding ChatMessageType Enum
 * All methods are realized by reflection.
 * @author berberman
 */
enum class NmsChatMessageType(val type: Byte) : IWrapper {
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

	override val handle: Any =
			ReflectionClasses.Nms.ChatMessageType()
					.invokeMethodSpecificTypes(null, "a",
							arrayOf(Byte::class.java as Class<*>), type)!!
}