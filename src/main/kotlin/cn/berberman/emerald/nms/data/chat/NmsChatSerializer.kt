package cn.berberman.emerald.nms.data.chat

import cn.berberman.emerald.extension.getConstructorAccess
import cn.berberman.emerald.extension.invokeMethod
import cn.berberman.emerald.nms.NmsReflection
import cn.berberman.emerald.nms.NmsUtil
import org.bukkit.plugin.IllegalPluginAccessException

class NmsChatSerializer : NmsReflection() {

	companion object {
		fun decodeFromString(raw: String) = NmsUtil.getNMSClass("IChatBaseComponent\$ChatSerializer").invokeMethod(
				null, "a", raw
		)?.let { NmsIChatBaseComponentEx(it) } ?: throw IllegalPluginAccessException("Convert error.")
	}

	override val targetNMSClass: Class<*> = NmsUtil.getNMSClass("IChatBaseComponent\$ChatSerializer")

	override val instanceNMS: Any = targetNMSClass.getConstructorAccess().newInstance()
}