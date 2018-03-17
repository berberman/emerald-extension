package cn.berberman.emerald.reflection

import com.esotericsoftware.reflectasm.ConstructorAccess
import com.esotericsoftware.reflectasm.FieldAccess
import com.esotericsoftware.reflectasm.MethodAccess

/**
 * Get a class's method access.
 * @see MethodAccess
 * @return methodAccess
 */
fun <T : Any> Class<T>.getMethodAccess(): MethodAccess = MethodAccess.get(this)

/**
 * Get a class's field access.
 * @see FieldAccess
 * @return fieldAccess
 */
fun <T : Any> Class<T>.getFieldAccess(): FieldAccess = FieldAccess.get(this)

/**
 * Get a class's constructor access.
 * @see ConstructorAccess
 * @return constructorAccess
 */
fun <T : Any> Class<T>.getConstructorAccess(): ConstructorAccess<T> = ConstructorAccess.get(this)

/**
 * Invoke a non-private method by using asm reflection.
 * @param instance object
 * @param name method name
 * @param parameters method's parameters
 * @return method's return value
 */
fun <T : Any> Class<T>.invokeMethod(instance: Any?, name: String, vararg parameters: Any?): Any? =
		getMethodAccess()(instance, name, *parameters)

/**
 * Get a non-private field by using asm reflection.
 * @param instance object
 * @param name field name
 */
fun <T : Any> Class<T>.getField(instance: Any, name: String): Any? = getFieldAccess()[instance, name]
