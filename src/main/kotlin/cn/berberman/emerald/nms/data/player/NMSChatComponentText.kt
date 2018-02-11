package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSUtil
import cn.berberman.emerald.nms.data.player.interfaces.NMSIChatBaseComponent
import java.lang.reflect.Method

/**
 * Corresponding ChatComponentText
 * All methods are realized by reflection.
 * @author berberman
 * @param text the text
 */
class NMSChatComponentText(val text: String) : NMSIChatBaseComponent() {


	override val methods: HashMap<String, Method> = hashMapOf()
	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val targetNMSClass: Class<*> = NMSUtil.getNMSClass("ChatComponentText")

	override val nmsChat:Any = targetNMSClass.getConstructor(String::class.java).newInstance(text)

}