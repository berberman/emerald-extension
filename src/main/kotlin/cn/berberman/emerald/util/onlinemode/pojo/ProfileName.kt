package cn.berberman.emerald.util.onlinemode.pojo

import com.google.gson.annotations.SerializedName


data class ProfileName(
		@SerializedName("id") val uuid: String,
		@SerializedName("name") val name: String
)