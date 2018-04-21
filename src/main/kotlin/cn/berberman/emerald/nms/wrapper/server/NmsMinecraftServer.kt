package cn.berberman.emerald.nms.wrapper.server

import cn.berberman.emerald.extension.unsafeCast
import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.invokeMethod
import org.bukkit.Bukkit

object NmsMinecraftServer : ReflectionWrapper() {

	override val clazz: Class<*> = ReflectionClasses.Nms.MinecraftServer()

	override val handle: Any = ReflectionClasses.CraftBukkit.CraftServer().invokeMethod(
			Bukkit.getServer(),
			"getServer"
	)!!

	val primaryThread: Thread = fields("primaryThread")!!.unsafeCast<Thread>()
}