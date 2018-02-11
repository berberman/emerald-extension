package cn.berberman.emerald.nms.data.player

import cn.berberman.emerald.nms.NMSReflection
import cn.berberman.emerald.nms.NMSUtil
import org.bukkit.entity.Player
import java.lang.reflect.Method

/**
 * Corresponding CraftPlayer
 * All methods are realized by reflection.
 * Convert from bukkit player.
 * @author berberman
 * @param player bukkit player
 */
class NMSCraftPlayer(val player: Player) : NMSReflection() {
	override val targetNMSClass: Class<*> = NMSUtil.getNMSClass("CraftPlayer")
	override val methods: HashMap<String, Method> = hashMapOf(
			"getHandle" to getMethod("getHandle")
	)

	/**
	 * Get entity player
	 * @return entity player
	 */
	fun getHandle() = NMSEntityPlayer(methods("getHandle")(player))
}