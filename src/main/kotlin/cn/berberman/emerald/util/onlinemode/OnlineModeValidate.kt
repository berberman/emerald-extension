package cn.berberman.emerald.util.onlinemode

import HttpUtil
import cn.berberman.emerald.coroutine.SchedulerContxt
import cn.berberman.emerald.dsl.event.createEventListener
import com.google.gson.Gson
import kotlinx.coroutines.experimental.launch
import org.bukkit.event.player.PlayerLoginEvent

internal object OnlineModeValidate {

	internal val players = mutableMapOf<String, String>()

	internal val joinEvent = createEventListener<PlayerLoginEvent> {
		launch(SchedulerContxt) {
			HttpUtil.get("https://api.mojang.com/users/profiles/minecraft/${player.name}") {
				it.entity?.let { Gson().fromJson(it.content.bufferedReader(), ProfileData::class.java) }
			}.let { players[player.name] = it?.uuid ?: "" }
		}
	}
}