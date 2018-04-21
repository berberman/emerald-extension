package cn.berberman.emerald.tallraw

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.util.*

class TellrawMessageSerializer : JsonSerializer<TellrawMessage> {

	override fun serialize(tellraw: TellrawMessage, type: Type, context: JsonSerializationContext): JsonElement {
		val obj = JsonObject()
		if (tellraw.color != TellrawChatColor.WHITE) {
			obj.addProperty("color", tellraw.color.jsonName)
		}
		if (tellraw.bold) {
			obj.addProperty("bold", tellraw.bold)
		}
		if (tellraw.italic) {
			obj.addProperty("italic", tellraw.italic)
		}
		if (tellraw.underlined) {
			obj.addProperty("underlined", tellraw.underlined)
		}
		if (tellraw.strikethrough) {
			obj.addProperty("strikethrough", tellraw.strikethrough)
		}
		if (tellraw.obfuscated) {
			obj.addProperty("obfuscated", tellraw.obfuscated)
		}
		if (tellraw.insertion.isNotBlank()) {
			obj.addProperty("insertion", tellraw.insertion)
		}
		if (tellraw.clickEvent != null) {
			val clickEvent = JsonObject()
			clickEvent.addProperty("action", tellraw.clickEvent!!.action.toString().toLowerCase(Locale.ROOT))
			clickEvent.addProperty("value", tellraw.clickEvent!!.value)
			obj.add("clickEvent", clickEvent)
		}
		if (tellraw.hoverEvent != null) {
			val hoverEvent = JsonObject()
			hoverEvent.addProperty("action", tellraw.hoverEvent!!.action.toString().toLowerCase(Locale.ROOT))
			hoverEvent.add("value", context.serialize(tellraw.hoverEvent!!.value))
			obj.add("hoverEvent", hoverEvent)
		}

		obj.addProperty("text", tellraw.text)

		return obj
	}

}