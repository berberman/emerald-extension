package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.nms.wrapper.IWrapper
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.invokeMethod

enum class NmsEnumHand : IWrapper {
	MAIN_HAND,
	OFF_HAND;

	override val handle: Any =
			ReflectionClasses.NmsClass.EnumHand()
					.invokeMethod(null, "valueOf", name)!!
}
