package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSAReflection
import cn.berberman.emerald.nms.NMSAUtil
import org.bukkit.entity.Player

/**
 * Corresponding CraftPlayer
 * All methods are realized by reflection.
 * Convert from bukkit player.
 * @author berberman
 * @param player bukkit player
 */
class NMSCraftPlayer(val player: Player) : NMSAReflection() {
	override val targetNMSClass: Class<*> = NMSAUtil.getCraftBukkitClass("entity.CraftPlayer")

	override val instanceNMS: Any
		get() = player

	/**
	 * Get entity player
	 * @return entity player
	 */
	fun getHandle() = NMSEntityPlayer(methods("getHandle")!!)
}