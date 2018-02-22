package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NmsUtil
import cn.berberman.emerald.nms.data.chat.NmsChatMessageType
import cn.berberman.emerald.nms.data.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.nms.data.chat.interfaces.NmsPacket

/**
 * Corresponding PacketPlayOutChat
 * All methods are realized by reflection.
 * @author berberman
 * @param chatBaseComponent text
 * @param chatMessageType message type
 */
class NmsPacketPlayOutChat(chatBaseComponent: NmsIChatBaseComponent, chatMessageType: NmsChatMessageType) : NmsPacket() {
	override val targetNMSClass: Class<*> = NmsUtil.getNMSClass("PacketPlayOutChat")


	override val nmsPacket: Any = NmsUtil.getNMSClass("IChatBaseComponent").let {
		if (shouldUseNewFormat())
			targetNMSClass.getConstructor(it,
					NmsUtil.getNMSClass("ChatMessageType")).newInstance(chatBaseComponent.nmsChat,
					chatMessageType.getNMSInstance())
		else targetNMSClass.getConstructor(it, Byte::class.java)
				.newInstance(chatBaseComponent, chatMessageType.type)
	}

	private fun shouldUseNewFormat() = NmsUtil.version.let {
		it.split("_")[1] >= "12"
	}
}