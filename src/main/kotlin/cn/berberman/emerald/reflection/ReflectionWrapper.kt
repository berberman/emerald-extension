package cn.berberman.emerald.reflection

/**
 * An abstract class, describe what should a class that using reflection to corresponding nms class has.
 * @author berberman
 */
abstract class ReflectionWrapper {
	/**
	 * Which class we want's to corresponding.
	 */
	protected abstract val clazz: Class<*>

	/**
	 * NMS instance
	 */
	abstract val instance: Any

	/**
	 * Invoke a method in target class
	 */
	fun methods(name: String, vararg parameter: Any?): Any? =
			clazz.invokeMethod(instance, name, *parameter)

	fun fields(name: String): Any? = clazz.getFieldAccess()[instance, name]

	operator fun invoke() = clazz
}