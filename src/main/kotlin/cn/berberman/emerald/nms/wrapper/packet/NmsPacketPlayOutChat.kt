package cn.berberman.emerald.nms.wrapper.packet

import cn.berberman.emerald.nms.wrapper.chat.NmsChatMessageType
import cn.berberman.emerald.nms.wrapper.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.reflection.ReflectionClasses.Nms.*
import cn.berberman.emerald.util.NmsUtil

/**
 * Corresponding PacketPlayOutChat
 * All methods are realized by reflection.
 * @author berberman
 */
class NmsPacketPlayOutChat : NmsPacket {
	override val nmsPacket: Any

	override val clazz: Class<*> = PacketPlayOutChat()

	constructor(chatBaseComponent: NmsIChatBaseComponent) {
		nmsPacket = clazz.getConstructor(IChatBaseComponent()).newInstance(chatBaseComponent.nmsChat)
	}

	/**
	 * @param chatBaseComponent text
	 * @param chatMessageType message type
	 */
	constructor(chatBaseComponent: NmsIChatBaseComponent, chatMessageType: NmsChatMessageType) {
		nmsPacket = IChatBaseComponent().let {
			if (shouldUseNewFormat())
				clazz.getConstructor(it,
						ChatMessageType()).newInstance(chatBaseComponent.nmsChat,
						chatMessageType.handle)
			else clazz.getConstructor(it, Byte::class.java)
					.newInstance(chatBaseComponent, chatMessageType.type)
		}


	}

	private fun shouldUseNewFormat() = NmsUtil.version.split("_")[1] >= "12"

}