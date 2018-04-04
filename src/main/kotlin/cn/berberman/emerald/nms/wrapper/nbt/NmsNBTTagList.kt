package cn.berberman.emerald.nms.wrapper.nbt

import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.reflection.getConstructorAccess
import cn.berberman.emerald.util.ReflectionUtil

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
	override val clazz: Class<*> = ReflectionClasses.NmsClass.NBTTagList()

	/**
	 * Base constructor, which will new a NBTTagList instance.
	 */
	constructor() {
		handle = clazz.getConstructorAccess().newInstance()
		internalList = ReflectionUtil.getField(clazz, "list", handle)
	}

	/**
	 * Advance constructor, use an initialed instance.
	 * @param original a NBTTagList instance.
	 */
	constructor(original: Any) {
		handle = original
		internalList = ReflectionUtil.getField(clazz, "list", handle)
	}

	override val handle: Any

	private val internalList: ArrayList<*>

	/**
	 * remove a member from list.
	 * @param index the index of target member.
	 */
	fun remove(index: Int) {
		methods("remove", index)
	}

	/**
	 * remove a member to list.
	 * @param any a NBTBase instance that you'd to add it to the list.
	 */
	fun add(any: Any) {
		methods("add", any)
	}

	fun get(index: Int) = methods("get", index)


	//Internal
	internal fun getInternal() = internalList

	fun internalClear() = internalList.clear()

	fun internalSize() = internalList.size

}
