package cn.berberman.emerald.nms.data.nbt

import cn.berberman.emerald.extension.getConstructorAccess
import cn.berberman.emerald.nms.NmsReflection
import cn.berberman.emerald.util.NmsUtil

/**
 * Corresponding NBTTagList
 * All methods are realized by reflection.
 *
 * @author berberman
 */
class NmsNBTTagList : NmsReflection {
	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val targetNMSClass: Class<*> = NmsUtil.getNMSClass("NBTTagList")

	/**
	 * Base constructor, which will new a NBTTagList instance.
	 */
	constructor() {
		instanceNMS = targetNMSClass.getConstructorAccess().newInstance()
	}

	/**
	 * Advance constructor, use an initialed instance.
	 * @param original a NBTTagList instance.
	 */
	constructor(original: Any) {
		instanceNMS = original
	}

	override val instanceNMS: Any

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
