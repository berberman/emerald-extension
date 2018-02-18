package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSAUtil
import cn.berberman.emerald.nms.data.player.interfaces.NMSIChatBaseComponent
import cn.berberman.emerald.nms.data.player.interfaces.NMSPacket

/**
 * Corresponding PacketPlayOutChat
 * All methods are realized by reflection.
 * @author berberman
 * @param chatBaseComponent text
 * @param chatMessageType message type
 */
class NMSPacketPlayOutChat(chatBaseComponent: NMSIChatBaseComponent, chatMessageType: NMSChatMessageType) : NMSPacket() {
	override val targetNMSClass: Class<*> = NMSAUtil.getNMSClass("PacketPlayOutChat")


	override val nmsPacket: Any = NMSAUtil.getNMSClass("IChatBaseComponent").let {
		if (shouldUseNewFormat())
			targetNMSClass.getConstructor(it,
					NMSAUtil.getNMSClass("ChatMessageType")).newInstance(chatBaseComponent.nmsChat,
					chatMessageType.getNMSInstance())
		else targetNMSClass.getConstructor(it, Byte::class.java)
				.newInstance(chatBaseComponent, chatMessageType.type)
	}

	private fun shouldUseNewFormat() = NMSAUtil.version.let {
		it.split("_")[1] >= "12"
	}
}