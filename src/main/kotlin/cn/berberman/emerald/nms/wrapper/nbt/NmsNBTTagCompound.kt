package cn.berberman.emerald.nms.wrapper.nbt

import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.reflection.getConstructorAccess
import cn.berberman.emerald.util.ReflectionUtil


/**
 * Corresponding NBTTagCompound
 * All methods are realized by reflection.
 * @author berberman
 */
class NmsNBTTagCompound : ReflectionWrapper {
	/**
	 * internal property to save corresponding nms class.<br>
	 *     You can't access this property, because it's inherited from NMSReflection.
	 */
	override val clazz: Class<*> = ReflectionClasses.Nms.NBTTagCompound()

	/**
	 * Base constructor, which will new a NBTTagCompound instance.
	 */
	constructor() {
		handle = clazz.getConstructorAccess().newInstance()
		internalMap = ReflectionUtil.getField(clazz, "map", handle)
	}

	/**
	 * Advance constructor, use an initialed instance.
	 * @param original a NBTTagCompound instance.
	 */
	constructor(original: Any) {
		handle = original
		internalMap = ReflectionUtil.getField(clazz, "map", handle)
	}

	override val handle: Any

	private val internalMap: HashMap<*, *>
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

	fun setDouble(name: String, value: Double) {
		methods("setDouble", name, value)
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

	fun get(name: String): Any? = methods("get", name)

	//internal
	internal fun getInternal() = internalMap

	fun internalClear() = internalMap.clear()
}
