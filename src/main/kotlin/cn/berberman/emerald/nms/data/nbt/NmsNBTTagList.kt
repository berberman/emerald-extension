package cn.berberman.emerald.nms.data.nbt

import cn.berberman.emerald.reflection.getConstructorAccess
import cn.berberman.emerald.reflection.ReflectionWrapper
import cn.berberman.emerald.util.NmsUtil

/**
 * Corresponding NBTTagList
 * All methods are realized by reflection.
 *
 * @author berberman
 */
class NmsNBTTagList : ReflectionWrapper {
	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val clazz: Class<*> = NmsUtil.getNMSClass("NBTTagList")

	/**
	 * Base constructor, which will new a NBTTagList instance.
	 */
	constructor() {
		instance = clazz.getConstructorAccess().newInstance()
	}

	/**
	 * Advance constructor, use an initialed instance.
	 * @param original a NBTTagList instance.
	 */
	constructor(original: Any) {
		instance = original
	}

	override val instance: Any

	/**
	 * remove a member from list.
	 * @param int the index of target member.
	 */
	fun remove(int: Int) {
		methods("remove", int)
	}

	/**
	 * remove a member to list.
	 * @param any a NBTBase instance that you'd to add it to the list.
	 */
	fun add(any: Any) {
		methods("add", any)
	}
}
