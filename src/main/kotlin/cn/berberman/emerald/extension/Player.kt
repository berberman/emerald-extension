@file:JvmName("PlayerUtil")

package cn.berberman.emerald.extension

import cn.berberman.emerald.nms.wrapper.chat.NmsChatComponentText
import cn.berberman.emerald.nms.wrapper.chat.NmsChatMessageType
import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.nms.wrapper.packet.NmsPacketPlayOutChat
import cn.berberman.emerald.nms.wrapper.packet.NmsPacketPlayOutSetSlot
import cn.berberman.emerald.nms.wrapper.player.BukkitCraftPlayer
import cn.berberman.emerald.nms.wrapper.player.NmsEnumHand
import cn.berberman.emerald.util.onlinemode.OnlineModeValidate
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
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
@Deprecated("Low performance", ReplaceWith("sendComponentChat", "cn.berberman.emerald.extension.sendComponentChat"))
fun Player.sendActionBar(text: String) =
		toCraftPlayer()
				.getHandle()
				.playerConnection
				.sendPacket(NmsPacketPlayOutChat(NmsChatComponentText(text)
						, NmsChatMessageType.GAME_INFO))

fun Player.sendComponentActionBar(vararg components: BaseComponent) =
		spigot().sendMessage(ChatMessageType.ACTION_BAR, *components)

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

fun Player.openBook(itemStack: ItemStack, enumHand: NmsEnumHand) = toCraftPlayer().getHandle().openBook(itemStack, enumHand)


val Player.isOnlineMode
	get() = OnlineModeValidate.players[name]?.equals(uniqueId.toString()) ?: false

@Deprecated("magic", ReplaceWith("broadcastCarriedItem()"))
fun Player.sendItemMessage(item: ItemStack) {
	toCraftPlayer().getHandle().playerConnection.sendPacket(NmsPacketPlayOutSetSlot(-1, -1, NmsItemStack(item)))
}
