package cn.berberman.emerald.nmsItem.data

import cn.berberman.emerald.extension.invoke
import cn.berberman.emerald.nmsItem.NMSReflection
import cn.berberman.emerald.nmsItem.NMSUtil
import java.lang.reflect.Method

/**
 * Corresponding NBTTagCompound
 * All methods are realized by reflection.
 * @author berberman
 */
class NMSNBTTagCompound : NMSReflection {
	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val targetNMSClass: Class<*> = NMSUtil.getNMSClass("NBTTagCompound")

	/**
	 * Base constructor, which will new a NBTTagCompound instance.
	 */
	constructor() {
		tagCompound = targetNMSClass.newInstance()
	}

	/**
	 * Advance constructor, use an initialed instance.
	 * @param original a NBTTagCompound instance.
	 */
	constructor(original: Any) {
		tagCompound = original
	}

	/**
	 * an instance of NBTTagCompound holds by this class.
	 */
	val tagCompound: Any
	/**
	 * internal property to save all methods.<br>
	 *  You can't access this property, because it's inherited from NMSReflection.
	 */
	override val rawMethods: Array<out Method> = targetNMSClass.methods

	/**
	 * internal function to get methods instance.
	 *     You can't access this method, because it's inherited from NMSReflection.
	 */
	override fun getMethod(name: String) = rawMethods.first { it.name == name }

	/**
	 * internal property to save realized methods
	 *  You can't access this property, because it's inherited from NMSReflection.
	 */
	override val methods = hashMapOf(
			"setInt" to getMethod("setInt"),
			"setString" to getMethod("setString"),
			"remove" to getMethod("remove"),
			"set" to getMethod("set")
	)

	/**
	 * set int value
	 * @param name the key of value.
	 * @param value value that you wan's to set.
	 */
	fun setInt(name: String, value: Int) {
		methods("setInt")(tagCompound, name, value)
	}

	/**
	 * set String value
	 * @param name the key of value.
	 * @param value value that you wan's to set.
	 */
	fun setString(name: String, value: String) {
		methods("setString")(tagCompound, name, value)
	}

	/**
	 * remove a value from this compound.
	 * @param name the key of value.
	 */
	fun remove(name: String) {
		methods("remove")(tagCompound, name)
	}

	/**
	 * set raw value
	 * @param name the key of value.
	 * @param any NBTBase instance
	 */
	fun set(name: String, any: Any) {
		methods("set")(tagCompound, name, any)
	}

}
