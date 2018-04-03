package cn.berberman.emerald.util.onlinemode

import com.google.gson.annotations.SerializedName


data class ProfileData(
		@SerializedName("id") val uuid: String,
		@SerializedName("name") val name: String
)