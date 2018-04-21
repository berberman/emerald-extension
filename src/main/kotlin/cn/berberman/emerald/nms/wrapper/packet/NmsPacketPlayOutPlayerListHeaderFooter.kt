package cn.berberman.emerald.nms.wrapper.packet

import cn.berberman.emerald.nms.wrapper.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.getConstructorAccess

class NmsPacketPlayOutPlayerListHeaderFooter(header: NmsIChatBaseComponent,
                                             footer: NmsIChatBaseComponent) : NmsPacket() {
	override val clazz: Class<*> = ReflectionClasses.Nms.PacketPlayOutPlayerListHeaderFooter()

	override val nmsPacket: Any = clazz.getConstructorAccess().newInstance()

	init {
		clazz.getDeclaredField("a").also { it.isAccessible = true }[handle] = header.nmsChat

		clazz.getDeclaredField("b").also { it.isAccessible = true }[handle] = footer.nmsChat
	}

}