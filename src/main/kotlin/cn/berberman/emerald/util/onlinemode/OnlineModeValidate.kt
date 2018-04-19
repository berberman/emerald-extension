package cn.berberman.emerald.util.onlinemode

import cn.berberman.emerald.coroutine.SchedulerContxt
import cn.berberman.emerald.dsl.event.eventListener
import cn.berberman.emerald.extension.debug
import kotlinx.coroutines.experimental.launch
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerLoginEvent
import java.util.concurrent.ConcurrentHashMap

object OnlineModeValidate {

	internal val players = ConcurrentHashMap<String, String>()

	val loginEvent = eventListener<PlayerLoginEvent> {
		validate(player)
	}

	private fun validate(player: Player) {
		launch(SchedulerContxt) {
			AuthenticationUtil.getPlayerUUID(player.name).let { players[player.name] = it?.uuid ?: "" }
		}.invokeOnCompletion { debug { "validate $player finished" } }
	}
}
