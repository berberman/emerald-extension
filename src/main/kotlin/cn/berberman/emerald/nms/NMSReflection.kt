package cn.berberman.emerald.nms

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
	protected open val rawMethods: Array<out Method>
		get() = targetNMSClass.methods

	/**
	 * Find a method from rawMethods by name.
	 * @param name method name
	 */
	protected open fun getMethod(name: String) =
			rawMethods.first { it.name == name }


	/**
	 * Methods we want's to use.
	 */
	protected abstract val methods: HashMap<String, Method>

	protected operator fun Map<String, Method>.invoke(name: String): Method = get(name)!!
}