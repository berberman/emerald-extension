package cn.berberman.emerald.nms.wrapper

import cn.berberman.emerald.reflection.ReflectionWrapper
import cn.berberman.emerald.util.EmeraldUtil.pool
import cn.berberman.emerald.util.NmsUtil

@Deprecated("unreachable", level = DeprecationLevel.HIDDEN)
abstract class NmsPathfinderGoal : ReflectionWrapper() {
	override val clazz: Class<*> = NmsUtil.getNMSClass("PathfinderGoal")

	init {
		val clazz = pool.makeClass("")
		clazz.superclass = pool["net.minecraft.server.${NmsUtil.version}"]
	}

	abstract fun shouldExecute(): Boolean

	abstract fun check(): Boolean

	abstract fun update()

	abstract fun finalize()
}