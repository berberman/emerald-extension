package cn.berberman.emerald.nms.wrapper.chat

import cn.berberman.emerald.nms.wrapper.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.util.NmsUtil

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
	override val clazz: Class<*> = ReflectionClasses.NmsClass.ChatComponentText()

	override val nmsChat: Any = clazz.getConstructor(String::class.java).newInstance(text)

}