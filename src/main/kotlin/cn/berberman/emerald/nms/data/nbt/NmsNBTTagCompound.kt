package cn.berberman.emerald.nms.data.nbt

import cn.berberman.emerald.extension.getConstructorAccess
import cn.berberman.emerald.nms.NmsReflection
import cn.berberman.emerald.util.NmsUtil

/**
 * Corresponding NBTTagCompound
 * All methods are realized by reflection.
 * @author berberman
 */
class NmsNBTTagCompound : NmsReflection {
	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val targetNMSClass: Class<*> = NmsUtil.getNMSClass("NBTTagCompound")

	/**
	 * Base constructor, which will new a NBTTagCompound instance.
	 */
	constructor() {
		instanceNMS = targetNMSClass.getConstructorAccess().newInstance()
	}

	/**
	 * Advance constructor, use an initialed instance.
	 * @param original a NBTTagCompound instance.
	 */
	constructor(original: Any) {
		instanceNMS = original
	}

	override val instanceNMS: Any

	/**
	 * set int value
	 * @param name the key of value.
	 * @param value value that you wan's to set.
	 */
	fun setInt(name: String, value: Int) {
		methods("setInt", name, value)
	}

	/**
	 * set String value
	 * @param name the key of value.
	 * @param value value that you wan's to set.
	 */
	fun setString(name: String, value: String) {
		methods("setString", name, value)
	}

	/**
	 * remove a value from this compound.
	 * @param name the key of value.
	 */
	fun remove(name: String) {
		methods("remove", name)
	}

	/**
	 * set raw value
	 * @param name the key of value.
	 * @param any NBTBase instance
	 */
	fun set(name: String, any: Any) {
		methods("set", name, any)
	}

}
