package cn.berberman.emerald.nms.data.chat

import cn.berberman.emerald.nms.data.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.util.NmsUtil

class NmsIChatBaseComponentEx(nmsMsg: Any) : NmsIChatBaseComponent() {

	override val nmsChat: Any = nmsMsg

	override val clazz: Class<*> = NmsUtil.getNMSClass("IChatBaseComponent")
}