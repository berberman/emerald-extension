package cn.berberman.emerald.nms.wrapper

import cn.berberman.emerald.reflection.getFieldAccess
import cn.berberman.emerald.reflection.invokeMethod

/**
 * An abstract class, describe what should a class that using reflection to corresponding nms class has.
 * @author berberman
 */
abstract class ReflectionWrapper : IWrapper {
	/**
	 * Which class we want's to corresponding.
	 */
	protected abstract val clazz: Class<*>


	/**
	 * Invoke a method in target class
	 */
	fun methods(name: String, vararg parameter: Any?): Any? =
			clazz.invokeMethod(handle, name, *parameter)

	fun fields(name: String): Any? = clazz.getFieldAccess()[handle, name]

}