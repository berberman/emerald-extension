package cn.berberman.emerald.reflection

import cn.berberman.emerald.util.NmsUtil

object ReflectionClasses {

	private interface ClassEnum {
		val clazz: Class<*>

		operator fun invoke() = clazz
	}

	enum class Nms(className: String) : ClassEnum {
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
		EnumHand("EnumHand"),
		NBTTagString("NBTTagString"),
		PacketPlayOutNamedEntitySpawn("PacketPlayOutNamedEntitySpawn"),
		PacketPlayOutPlayerInfo("PacketPlayOutPlayerInfo"),
		EnumPlayerInfoAction("EnumPlayerInfoAction"),
		PlayerInteractManager("NmsPlayerInteractManager"),
		WorldServer("WorldServer");

		override val clazz = NmsUtil.getNMSClass(className)
	}

	enum class CraftBukkit(className: String) : ClassEnum {
		CraftPlayer("entity.CraftPlayer"),
		CraftMetaBook("inventory.CraftMetaBook"),
		CraftItemStack("inventory.CraftItemStack"),
		CraftWorld("CraftWorld"),
		CraftServer("CraftServer");

		override val clazz: Class<*> = NmsUtil.getCraftBukkitClass(className)
	}

	enum class MojangAuthLib(className: String) : ClassEnum {
		Property("properties.Property"),
		GameProfile("GameProfile");

		override val clazz: Class<*> = Class.forName("com.mojang.authlib.$className")
	}
}