package cn.berberman.emerald.util

import cn.berberman.emerald.extension.unsafeCast
import java.lang.reflect.Method

object ReflectionUtil {

	inline fun <reified T, reified R> getField(name: String, instance: T?): R =
			T::class.java.getDeclaredField(name).apply {
				isAccessible = true
			}[instance].unsafeCast()

	inline fun <reified R> getField(clazz: Class<*>, name: String, instance: Any?): R =
			clazz.getDeclaredField(name).apply {
				isAccessible = true
			}[instance].unsafeCast()

	inline fun <reified T> getMethod(clazz: Class<T> = T::class.java, name: String, vararg parameter: Class<*>?): Method =
			clazz.getDeclaredMethod(name, *parameter).apply {
				isAccessible = true
			}

	inline fun <reified T> getMethod(clazz: Class<T> = T::class.java, name: String): Method =
			clazz.declaredMethods.filter { it.name == name }.let {
				if (it.size != 1)
					throw NoSuchMethodException("Unexpected method amount!")
				else it[0]
			}
}