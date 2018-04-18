package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.nms.wrapper.world.NmsWorldServer
import cn.berberman.emerald.reflection.ReflectionClasses

class NmsPlayerInteractManager(worldServer: NmsWorldServer) : ReflectionWrapper() {

	override val clazz: Class<*> = ReflectionClasses.Nms.PlayerInteractManager()

	override val handle: Any = clazz.constructors[0].newInstance(worldServer.handle)

}