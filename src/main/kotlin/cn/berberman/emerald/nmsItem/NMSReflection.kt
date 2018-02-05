package cn.berberman.emerald.nmsItem

import java.lang.reflect.Method

/**
 * An abstract class, describe what should a class that using reflection to corresponding nms class have.
 * @author berberman
 */
abstract class NMSReflection {
	/**
	 * Which class we want's to corresponding.
	 */
	protected abstract val targetNMSClass: Class<*>
	/**
	 * All methods of target class.
	 */
	protected abstract val rawMethods: Array<out Method>

	/**
	 * Find a method from rawMethods by name.
	 * @param name method name
	 */
	protected abstract fun getMethod(name: String): Method

	/**
	 * Methods we want's to use.
	 */
	protected abstract val methods: HashMap<String, Method>
}