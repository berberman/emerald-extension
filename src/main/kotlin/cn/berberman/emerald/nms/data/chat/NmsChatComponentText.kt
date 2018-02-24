package cn.berberman.emerald.nms.data.chat

import cn.berberman.emerald.nms.data.chat.interfaces.NmsIChatBaseComponent
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
	override val targetNMSClass: Class<*> = NmsUtil.getNMSClass("ChatComponentText")

	override val nmsChat: Any = targetNMSClass.getConstructor(String::class.java).newInstance(text)

}