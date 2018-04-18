package cn.berberman.emerald.nms.wrapper.packet

import cn.berberman.emerald.nms.wrapper.chat.NmsChatMessageType
import cn.berberman.emerald.nms.wrapper.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.util.NmsUtil

/**
 * Corresponding PacketPlayOutChat
 * All methods are realized by reflection.
 * @author berberman
 * @param chatBaseComponent text
 * @param chatMessageType message type
 */
class NmsPacketPlayOutChat(chatBaseComponent: NmsIChatBaseComponent, chatMessageType: NmsChatMessageType) : NmsPacket() {
	override val clazz: Class<*> = ReflectionClasses.Nms.PacketPlayOutChat()


	override val nmsPacket: Any = ReflectionClasses.Nms.IChatBaseComponent().let {
		if (shouldUseNewFormat())
			clazz.getConstructor(it,
					ReflectionClasses.Nms.ChatMessageType()).newInstance(chatBaseComponent.nmsChat,
					chatMessageType.handle)
		else clazz.getConstructor(it, Byte::class.java)
				.newInstance(chatBaseComponent, chatMessageType.type)
	}

	private fun shouldUseNewFormat() = NmsUtil.version.split("_")[1] >= "12"

}