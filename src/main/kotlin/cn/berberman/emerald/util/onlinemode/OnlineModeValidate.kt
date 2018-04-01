package cn.berberman.emerald.util.onlinemode

import HttpUtil
import cn.berberman.emerald.coroutine.SchedulerContxt
import cn.berberman.emerald.dsl.event.eventListener
import cn.berberman.emerald.extension.fromJson
import com.google.gson.Gson
import kotlinx.coroutines.experimental.launch
import org.bukkit.event.player.PlayerLoginEvent

internal object OnlineModeValidate {

	internal val players = mutableMapOf<String, String>()

	internal val loginEvent = eventListener<PlayerLoginEvent> {
		launch(SchedulerContxt) {
			HttpUtil.get("https://api.mojang.com/users/profiles/minecraft/${player.name}") {
				it.entity?.let { Gson().fromJson<ProfileData>(it.content.bufferedReader()) }
			}.let { players[player.name] = it?.uuid ?: "" }
		}
	}
}