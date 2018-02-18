package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSAUtil
import cn.berberman.emerald.nms.data.player.interfaces.NMSIChatBaseComponent

/**
 * Corresponding ChatComponentText
 * All methods are realized by reflection.
 * @author berberman
 * @param text the text
 */
class NMSChatComponentText(val text: String) : NMSIChatBaseComponent() {

	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val targetNMSClass: Class<*> = NMSAUtil.getNMSClass("ChatComponentText")

	override val nmsChat: Any = targetNMSClass.getConstructor(String::class.java).newInstance(text)

}