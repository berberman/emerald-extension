package cn.berberman.emerald.reflection

import cn.berberman.emerald.util.NmsUtil

object ReflectionClasses {

	private interface ClassEnum {
		val clazz: Class<*>

		operator fun invoke() = clazz
	}

	enum class NmsClass(className: String) : ClassEnum {
		ItemStack("ItemStack"),
		ChatComponentText("ChatComponentText"),
		ChatMessageType("ChatMessageType"),
		ChatSerializer("IChatBaseComponent\$ChatSerializer"),
		IChatBaseComponent("IChatBaseComponent"),
		NBTTagCompound("NBTTagCompound"),
		NBTTagList("NBTTagList"),
		EntityPlayer("EntityPlayer"),
		PacketPlayOutChat("PacketPlayOutChat"),
		PlayerConnection("PlayerConnection"),
		MinecraftServer("MinecraftServer"),
		PacketPlayOutSetSlot("PacketPlayOutSetSlot"),
		EnumHand("EnumHand");

		override val clazz = NmsUtil.getNMSClass(className)
	}

	enum class CraftBukkitClass(className: String) : ClassEnum {
		CraftPlayer("entity.CraftPlayer"),
		CraftMetaBook("inventory.CraftMetaBook"),
		CraftItemStack("inventory.CraftItemStack"),
		CraftServer("CraftServer");

		override val clazz: Class<*> = NmsUtil.getCraftBukkitClass(className)
	}
}