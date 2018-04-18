package cn.berberman.emerald.nms.wrapper.world

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.reflection.ReflectionClasses

class NmsWorldServer(worldServer: Any) : ReflectionWrapper() {
	override val clazz: Class<*> = ReflectionClasses.Nms.WorldServer()

	override val handle: Any = worldServer
}