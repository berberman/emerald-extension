package cn.berberman.emerald.nms.wrapper.chat

import cn.berberman.emerald.nms.wrapper.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.reflection.ReflectionClasses

/**
 * Corresponding ChatComponentText
 * All methods are realized by reflection.
 * @author berberman
 * @param text the text
 */
class NmsChatComponentText(val text: String) : NmsIChatBaseComponent() {

	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val clazz: Class<*> = ReflectionClasses.Nms.ChatComponentText()

	override val nmsChat: Any = clazz.getConstructor(String::class.java).newInstance(text)

}