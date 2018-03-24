package cn.berberman.emerald.nms.wrapper.player

import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.ReflectionWrapper
import org.bukkit.entity.Player

/**
 * Corresponding CraftPlayer
 * All methods are realized by reflection.
 * Convert from bukkit player.
 * @author berberman
 * @param player bukkit player
 */
class BukkitCraftPlayer(val player: Player) : ReflectionWrapper() {
	override val clazz: Class<*> = ReflectionClasses.CraftBukkitClass.CraftPlayer()

	override val instance: Any
		get() = player

	/**
	 * Get entity player
	 * @return entity player
	 */
	fun getHandle() = NmsEntityPlayer(methods("getHandle")!!)
}