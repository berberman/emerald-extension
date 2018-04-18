package cn.berberman.emerald.util.onlinemode

import cn.berberman.emerald.coroutine.SchedulerContxt
import cn.berberman.emerald.dsl.event.eventListener
import kotlinx.coroutines.experimental.launch
import org.bukkit.event.player.PlayerLoginEvent
import java.util.concurrent.ConcurrentHashMap

internal object OnlineModeValidate {

	internal val players = ConcurrentHashMap<String, String>()

	internal val loginEvent = eventListener<PlayerLoginEvent> {
		launch(SchedulerContxt) {
			AuthenticationUtil.getPlayerUUID(player.name).let { players[player.name] = it?.uuid ?: "" }
		}
	}
}
