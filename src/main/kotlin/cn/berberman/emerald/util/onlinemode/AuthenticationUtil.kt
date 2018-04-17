package cn.berberman.emerald.util.onlinemode

import HttpUtil
import cn.berberman.emerald.extension.fromJson
import cn.berberman.emerald.util.onlinemode.data.ProfileName
import cn.berberman.emerald.util.onlinemode.data.ProfileSkin
import com.google.gson.Gson

object AuthenticationUtil {

	suspend fun getPlayerUUID(name: String) =
			HttpUtil.get("https://api.mojang.com/users/profiles/minecraft/$name") {
				it.entity?.let { Gson().fromJson<ProfileName>(it.content.bufferedReader()) }
			}

	suspend fun getSkinWithSignature(uuid: String) =
			HttpUtil.get("https://sessionserver.mojang.com/session/minecraft/profile/$uuid") {
				it.entity?.let { Gson().fromJson<ProfileSkin>(it.content.bufferedReader()) }
			}
}