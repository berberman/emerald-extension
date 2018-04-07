package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.reflection.ReflectionClasses
import org.bukkit.entity.Player

/**
 * Corresponding CraftPlayer
 * All methods are realized by reflection.
 * Convert from bukkit player.
 * @author berberman
 * @param player bukkit player
 */
class BukkitCraftPlayer(player: Player) : ReflectionWrapper() {
	override val clazz: Class<*> = ReflectionClasses.CraftBukkitClass.CraftPlayer()

	override val handle: Any = player

	/**
	 * Get entity player
	 * @return entity player
	 */
	fun getHandle() = NmsEntityPlayer(methods("getHandle")!!)
}