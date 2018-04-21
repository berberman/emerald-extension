package cn.berberman.emerald.util

import cn.berberman.emerald.dsl.event.eventListener
import cn.berberman.emerald.events.player.PacketEvent
import cn.berberman.emerald.extension.toCraftPlayer
import io.netty.channel.ChannelDuplexHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelPromise
import org.bukkit.entity.Player
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

object ChannelHandler : ChannelDuplexHandler() {
	override fun channelRead(ctx: ChannelHandlerContext?, msg: Any) {
		PacketEvent.PacketPlayInEvent(msg).also(EmeraldUtil.pluginManager::callEvent).let {
			if (!it.isCancelled)
				super.channelRead(ctx, it.msg)
		}
	}

	override fun write(ctx: ChannelHandlerContext?, msg: Any, promise: ChannelPromise?) {
		PacketEvent.PacketPlayOutEvent(msg).also(EmeraldUtil.pluginManager::callEvent).let {
			if (!it.isCancelled)
				super.write(ctx, it.msg, promise)
		}
	}

	private fun addChannelHandler(player: Player) {
		player.toCraftPlayer().getHandle()
				.playerConnection.channel.pipeline()
				.addBefore("packet_handler", player.name, this)
	}

	private fun removeChannelHandler(player: Player) {
		player.toCraftPlayer().getHandle()
				.playerConnection.channel.let {
			it.eventLoop().submit { it.pipeline().remove(player.name) }
		}
	}

	internal val joinInject = eventListener<PlayerJoinEvent>(EventPriority.HIGHEST, true) {
		addChannelHandler(player)
	}
	internal val quitInject = eventListener<PlayerQuitEvent>(EventPriority.HIGHEST, true) {
		removeChannelHandler(player)
	}
}