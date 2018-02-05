package cn.berberman.emerald.extension.dsl.nms.item.data

import cn.berberman.emerald.extension.dsl.nms.item.NMSReflection
import cn.berberman.emerald.extension.dsl.nms.item.NMSUtil
import java.lang.reflect.Method

/**
 * Corresponding NBTTagList
 * All methods are realized by reflection.
 *
 * @author berberman
 */
class NMSNBTTagList : NMSReflection {
	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 * @see NMSReflection
	 */
	override val targetNMSClass: Class<*> = NMSUtil.getNMSClass("NBTTagList")

	/**
	 * Base constructor, which will new a NBTTagList instance.
	 */
	constructor() {
		tagList = targetNMSClass.newInstance()
	}

	/**
	 * Advance constructor, use a initialed instance.
	 * @param original a NBTTagList instance.
	 */
	constructor(original: Any) {
		tagList = original
	}

	/**
	 * a instance of NBTTagList holds by this class.
	 */
	val tagList: Any

	/**
	 * internal property to save all methods.
	 *  You can't access this property, because it's inherited from NMSReflection.
	 */
	override val rawMethods: Array<out Method> = targetNMSClass.methods

	/**
	 * internal function to get methods instance.
	 *     You can't access this method, because it's inherited from NMSReflection.
	 */
	override fun getMethod(name: String) = rawMethods.firstOrNull { it.name == name }!!

	/**
	 * internal property to save realized methods
	 *  You can't access this property, because it's inherited from NMSReflection.
	 */
	override val methods = hashMapOf(
			"remove" to getMethod("remove"),
			"add" to getMethod("add")
	)

	/**
	 * remove a member from list.
	 * @param int the index of target member.
	 */
	fun remove(int: Int) {
		methods["remove"]!!(tagList, int)
	}

	/**
	 * remove a member to list.
	 * @param any a NBTBase instance that you'd to add it to the list.
	 */
	fun add(any: Any) {
		methods["add"]!!(tagList, any)
	}
}
