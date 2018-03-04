package cn.berberman.emerald.nms.wrapper.chat

import cn.berberman.emerald.reflection.ReflectionWrapper
import cn.berberman.emerald.reflection.getConstructorAccess
import cn.berberman.emerald.reflection.invokeMethod
import cn.berberman.emerald.util.NmsUtil
import org.bukkit.plugin.IllegalPluginAccessException

class NmsChatSerializer : ReflectionWrapper() {

	companion object {
		fun decodeFromString(raw: String) = NmsUtil.getNMSClass("IChatBaseComponent\$ChatSerializer").invokeMethod(
				null, "a", raw
		)?.let { NmsIChatBaseComponentEx(it) } ?: throw IllegalPluginAccessException("Convert error.")
	}

	override val clazz: Class<*> = NmsUtil.getNMSClass("IChatBaseComponent\$ChatSerializer")

	override val instance: Any = clazz.getConstructorAccess().newInstance()
}