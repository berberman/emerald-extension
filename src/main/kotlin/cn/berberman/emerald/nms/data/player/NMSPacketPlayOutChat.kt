package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSUtil
import cn.berberman.emerald.nms.data.player.interfaces.NMSIChatBaseComponent
import cn.berberman.emerald.nms.data.player.interfaces.NMSPacket
import java.lang.reflect.Method

/**
 * Corresponding PacketPlayOutChat
 * All methods are realized by reflection.
 * @author berberman
 * @param chatBaseComponent text
 * @param chatMessageType message type
 */
class NMSPacketPlayOutChat(chatBaseComponent: NMSIChatBaseComponent, chatMessageType: NMSChatMessageType) : NMSPacket() {
	override val targetNMSClass: Class<*> = NMSUtil.getNMSClass("PacketPlayOutChat")

	override val methods: HashMap<String, Method> = hashMapOf()

	override val nmsPacket: Any = NMSUtil.getNMSClass("IChatBaseComponent").let {
		if (shouldUseNewFormat())
			targetNMSClass.getConstructor(it,
					NMSUtil.getNMSClass("ChatMessageType")).newInstance(chatBaseComponent.nmsChat,
					chatMessageType.getNMSInstance())
		else targetNMSClass.getConstructor(it, Byte::class.java)
				.newInstance(chatBaseComponent, chatMessageType.type)
	}

	private fun shouldUseNewFormat() = NMSUtil.version.let {
		it.split("_")[1] >= "12"
	}
}