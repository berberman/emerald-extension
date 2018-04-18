package cn.berberman.emerald.nms.wrapper.world

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.reflection.ReflectionClasses
import org.bukkit.World

class BukkitCraftWorld(world: World) : ReflectionWrapper() {

	override val clazz: Class<*> = ReflectionClasses.CraftBukkit.CraftWorld()

	override val handle: Any = world

	fun getHandle() = NmsWorldServer(methods("getHandle")!!)
}