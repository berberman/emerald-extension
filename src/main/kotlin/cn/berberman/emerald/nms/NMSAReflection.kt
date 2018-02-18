package cn.berberman.emerald.nms

import cn.berberman.emerald.extension.invokeMethod

/**
 * An abstract class, describe what should a class that using reflection to corresponding nms class have.
 * @author berberman
 */
abstract class NMSAReflection {
	/**
	 * Which class we want's to corresponding.
	 */
	protected abstract val targetNMSClass: Class<*>

	/**
	 * NMS instance
	 */
	abstract val instanceNMS: Any

	/**
	 * Invoke a method in target class
	 */
	fun methods(name: String, vararg parameterName: Any): Any? =
			targetNMSClass.invokeMethod(instanceNMS, name, *parameterName)
}