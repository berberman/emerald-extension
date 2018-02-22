package cn.berberman.emerald.extension

import cn.berberman.emerald.nms.data.chat.NmsChatComponentText
import cn.berberman.emerald.nms.data.chat.NmsChatMessageType
import cn.berberman.emerald.nms.data.player.BukkitCraftPlayer
import cn.berberman.emerald.nms.data.player.NmsPacketPlayOutChat
import org.bukkit.entity.Player

/**
 * Convert bukkit player to craft player.
 * @return craft player
 */
fun Player.toCraftPlayer() = BukkitCraftPlayer(this)

/**
 * Send an action bar to a player.
 */
fun Player.sendActionBar(text: String) =
		toCraftPlayer()
				.getHandle()
				.playerConnection
				.sendPacket(NmsPacketPlayOutChat(NmsChatComponentText(text)
						, NmsChatMessageType.GAME_INFO))