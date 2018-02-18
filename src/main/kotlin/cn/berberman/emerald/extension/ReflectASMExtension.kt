package cn.berberman.emerald.extension

import com.esotericsoftware.reflectasm.ConstructorAccess
import com.esotericsoftware.reflectasm.FieldAccess
import com.esotericsoftware.reflectasm.MethodAccess
import kotlin.reflect.KClass

fun <T : Any> KClass<T>.getMethodAccess() = MethodAccess.get(java)

fun <T : Any> KClass<T>.getFieldAccess() = FieldAccess.get(java)

fun <T : Any> KClass<T>.getConstructorAccess() = ConstructorAccess.get(java)


fun <T : Any> Class<T>.getMethodAccess(): MethodAccess = MethodAccess.get(this)

fun <T : Any> Class<T>.getFieldAccess(): FieldAccess = FieldAccess.get(this)

fun <T : Any> Class<T>.getConstructorAccess(): ConstructorAccess<T> = ConstructorAccess.get(this)

fun <T : Any> Class<T>.invokeMethod(instance: Any, name: String, vararg parameters: Any): Any? =
		getMethodAccess()(instance, name, *parameters)

fun <T : Any> Class<T>.getField(instance: Any, name: String): Any? = getFieldAccess()[instance, name]
