@file:JvmName("PlayerUtil")

package cn.berberman.emerald.extension

import cn.berberman.emerald.nms.wrapper.chat.NmsChatComponentText
import cn.berberman.emerald.nms.wrapper.chat.NmsChatMessageType
import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.nms.wrapper.packet.NmsPacketPlayOutChat
import cn.berberman.emerald.nms.wrapper.packet.NmsPacketPlayOutPlayerListHeaderFooter
import cn.berberman.emerald.nms.wrapper.packet.NmsPacketPlayOutSetSlot
import cn.berberman.emerald.nms.wrapper.player.BukkitCraftPlayer
import cn.berberman.emerald.nms.wrapper.player.NmsEnumHand
import cn.berberman.emerald.tallraw.TellrawMessage
import cn.berberman.emerald.util.onlinemode.OnlineModeValidate
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

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

fun Player.tellraw(tellrawMessage: TellrawMessage) =
		toCraftPlayer()
				.getHandle()
				.playerConnection
				.sendPacket(NmsPacketPlayOutChat(tellrawMessage.toIChatBaseComponent()))

fun Player.tellraw(text: String, builder: TellrawMessage.() -> Unit) =
		tellraw(tellrawMessage(text).apply(builder))


fun Player.sendComponentActionBar(vararg components: BaseComponent) =
		sendActionBar(components.joinToString { it.toLegacyText() })

fun Player.sendComponentActionBar(builder: ComponentBuilder) =
		spigot().sendMessage(ChatMessageType.ACTION_BAR, *builder.create())

fun Player.sendComponentActionBar(text: String, builder: ComponentBuilder.() -> Unit = {}) =
		spigot().sendMessage(ChatMessageType.ACTION_BAR, *componentChat(text, builder).create())

fun Player.sendComponentChat(vararg components: BaseComponent) =
		spigot().sendMessage(ChatMessageType.CHAT, *components)

fun Player.sendComponentChat(builder: ComponentBuilder) =
		spigot().sendMessage(ChatMessageType.CHAT, *builder.create())

fun Player.sendComponentChat(text: String, builder: ComponentBuilder.() -> Unit = {}) =
		spigot().sendMessage(ChatMessageType.CHAT, *componentChat(text, builder).create())


fun Player.broadcastCarriedItem() = toCraftPlayer().getHandle().broadcastCarriedItem()

fun Player.openBook(book: ItemStack) {
	val temp = inventory.itemInMainHand
	inventory.itemInMainHand = book
	toCraftPlayer().getHandle().openBook(NmsEnumHand.MAIN_HAND)
	inventory.itemInMainHand = temp
}

//Fixme bug
val Player.isOnlineMode
	get() = OnlineModeValidate.players.getOrDefault(name, "") == uniqueId.toString()

@Deprecated("magic", ReplaceWith("broadcastCarriedItem()"))
fun Player.sendItemMessage(item: ItemStack) {
	toCraftPlayer().getHandle().playerConnection.sendPacket(NmsPacketPlayOutSetSlot(-1, -1, NmsItemStack(item)))
}

fun allPlayers(operation: Player.() -> Unit) =
		Bukkit.getOnlinePlayers().forEach(operation)

fun Player.setPlayerListHeaderAndFooter(header: TellrawMessage, footer: TellrawMessage) {
	toCraftPlayer()
			.getHandle()
			.playerConnection
			.sendPacket(NmsPacketPlayOutPlayerListHeaderFooter(header.toIChatBaseComponent(), footer.toIChatBaseComponent()))
}
