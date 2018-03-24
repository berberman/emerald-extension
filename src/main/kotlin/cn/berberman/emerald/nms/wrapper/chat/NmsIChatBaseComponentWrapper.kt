package cn.berberman.emerald.nms.wrapper.chat

import cn.berberman.emerald.nms.wrapper.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.reflection.ReflectionClasses

class NmsIChatBaseComponentWrapper(nmsMsg: Any) : NmsIChatBaseComponent() {

	override val nmsChat: Any = nmsMsg

	override val clazz: Class<*> = ReflectionClasses.NmsClass.IChatBaseComponent()
}