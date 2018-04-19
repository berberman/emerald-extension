package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.nms.wrapper.packet.NmsPlayerConnection
import cn.berberman.emerald.nms.wrapper.world.NmsWorldServer
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.ReflectionClasses.Nms.*
import cn.berberman.emerald.reflection.getFieldAccess
import cn.berberman.emerald.reflection.invokeMethod
import cn.berberman.emerald.util.onlinemode.pojo.ProfileName
import cn.berberman.emerald.util.onlinemode.pojo.toGameProfile
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Corresponding EntityPlayer
 * All methods are realized by reflection.
 * Get from craft player.
 * @author berberman
 * @param nmsEntityPlayer nmsEntityPlayer, get from reflection [BukkitCraftPlayer]
 */
class NmsEntityPlayer(nmsEntityPlayer: Any) : ReflectionWrapper() {
	override val clazz: Class<*> = EntityPlayer()

	override val handle: Any = nmsEntityPlayer
	/**
	 * nms player connection instance
	 */
	val playerConnection = NmsPlayerConnection(clazz.getFieldAccess()[nmsEntityPlayer, "playerConnection"])

	private val nmsBook by lazy { NmsItemStack(ItemStack(Material.WRITTEN_BOOK)) }

	fun openBook(enumHand: NmsEnumHand) {
		clazz.invokeMethod(handle, "a", arrayOf(ItemStack(),
				EnumHand()), nmsBook.handle, enumHand.handle)
	}

	fun broadcastCarriedItem() {
		methods("broadcastCarriedItem")
	}

	companion object {
		fun new(worldServer: NmsWorldServer,
		        profileName: ProfileName, playerInteractManager: NmsPlayerInteractManager) = EntityPlayer().getConstructor(
				MinecraftServer(),
				WorldServer(),
				ReflectionClasses.MojangAuthLib.GameProfile(),
				PlayerInteractManager()
		).newInstance(worldServer.handle, profileName.toGameProfile(), playerInteractManager.handle).let(::NmsEntityPlayer)
	}
}