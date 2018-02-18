package cn.berberman.emerald.extension

import cn.berberman.emerald.nms.data.player.NMSChatComponentText
import cn.berberman.emerald.nms.data.player.NMSChatMessageType
import cn.berberman.emerald.nms.data.player.NMSCraftPlayer
import cn.berberman.emerald.nms.data.player.NMSPacketPlayOutChat
import org.bukkit.entity.Player

/**
 * Convert bukkit player to craft player.
 * @return craft player
 */
fun Player.toCraftPlayer() = NMSCraftPlayer(this)

/**
 * Send an action bar to a player.
 */
fun Player.sendActionBar(text: String) =
		toCraftPlayer()
				.getHandle()
				.playerConnection
				.sendPacket(NMSPacketPlayOutChat(NMSChatComponentText(text)
						, NMSChatMessageType.GAME_INFO))