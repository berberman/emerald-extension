package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.invokeMethod

enum class NmsEnumHand {
	MAIN_HAND,
	OFF_HAND;

	fun getNmsInstance(): Any =
			ReflectionClasses.NmsClass.EnumHand().invokeMethod(null, "valueOf", name)!!
}
