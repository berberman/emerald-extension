package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.reflection.ReflectionWrapper
import cn.berberman.emerald.util.NmsUtil
import org.bukkit.entity.Player

/**
 * Corresponding CraftPlayer
 * All methods are realized by reflection.
 * Convert from bukkit player.
 * @author berberman
 * @param player bukkit player
 */
class BukkitCraftPlayer(val player: Player) : ReflectionWrapper() {
	override val clazz: Class<*> = NmsUtil.getCraftBukkitClass("entity.CraftPlayer")

	override val instance: Any
		get() = player

	/**
	 * Get entity player
	 * @return entity player
	 */
	fun getHandle() = NmsEntityPlayer(methods("getHandle")!!)
}