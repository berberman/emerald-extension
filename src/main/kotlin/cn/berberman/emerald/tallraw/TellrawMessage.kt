package cn.berberman.emerald.tallraw

import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter


@JsonAdapter(TellrawMessageSerializer::class)
class TellrawMessage(val text: String) {
	var color: TellrawChatColor = TellrawChatColor.WHITE

	var bold: Boolean = false

	var italic: Boolean = false

	var underlined: Boolean = false

	var strikethrough: Boolean = false

	var obfuscated: Boolean = false

	var insertion: String = ""

	internal var clickEvent: TellrawClickEvent? = null

	internal var hoverEvent: TellrawHoverEvent? = null

	fun onHover(block: TellrawHoverEvent.() -> Unit) {
		hoverEvent = TellrawHoverEvent().apply(block)
	}

	fun onClick(block: TellrawClickEvent.() -> Unit) {
		clickEvent = TellrawClickEvent().apply(block)
	}

	fun toJsonString(): String = Gson().toJson(this)
}