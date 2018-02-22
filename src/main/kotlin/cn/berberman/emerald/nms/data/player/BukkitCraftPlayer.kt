package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NmsReflection
import cn.berberman.emerald.nms.NmsUtil
import org.bukkit.entity.Player

/**
 * Corresponding CraftPlayer
 * All methods are realized by reflection.
 * Convert from bukkit player.
 * @author berberman
 * @param player bukkit player
 */
class BukkitCraftPlayer(val player: Player) : NmsReflection() {
	override val targetNMSClass: Class<*> = NmsUtil.getCraftBukkitClass("entity.CraftPlayer")

	override val instanceNMS: Any
		get() = player

	/**
	 * Get entity player
	 * @return entity player
	 */
	fun getHandle() = NmsEntityPlayer(methods("getHandle")!!)
}