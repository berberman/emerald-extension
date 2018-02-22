package cn.berberman.emerald.nms.data.chat

import cn.berberman.emerald.nms.NmsUtil
import cn.berberman.emerald.nms.data.chat.interfaces.NmsIChatBaseComponent

class NmsIChatBaseComponentEx(nmsMsg: Any) : NmsIChatBaseComponent() {

	override val nmsChat: Any = nmsMsg

	override val targetNMSClass: Class<*> = NmsUtil.getNMSClass("IChatBaseComponent")
}