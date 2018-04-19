package cn.berberman.emerald.util.onlinemode

import HttpUtil
import cn.berberman.emerald.util.onlinemode.pojo.ProfileName
import cn.berberman.emerald.util.onlinemode.pojo.ProfileSkin
import toObject

object AuthenticationUtil {

	suspend fun getPlayerUUID(name: String): ProfileName? =
			HttpUtil.get("https://api.mojang.com/users/profiles/minecraft/$name") {
				it.entity?.toObject()
			}


	suspend fun getSkinWithSignature(uuid: String): ProfileSkin? =
			HttpUtil.get("https://sessionserver.mojang.com/session/minecraft/profile/$uuid") {
				it.entity?.toObject()
			}
}