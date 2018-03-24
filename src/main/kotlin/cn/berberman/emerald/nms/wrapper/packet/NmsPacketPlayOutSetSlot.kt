package cn.berberman.emerald.nms.wrapper.packet

import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.reflection.ReflectionClasses

class NmsPacketPlayOutSetSlot(var1: Int, var2: Int, itemStack: NmsItemStack) : NmsPacket() {

	override val clazz: Class<*> = ReflectionClasses.NmsClass.PacketPlayOutSetSlot()

	override val nmsPacket: Any =
			clazz.getConstructor(Int::class.java, Int::class.java,
					ReflectionClasses.NmsClass.ItemStack())
					.newInstance(var1, var2, itemStack.instance)

}