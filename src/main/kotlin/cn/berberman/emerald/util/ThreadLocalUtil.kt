package cn.berberman.emerald.util

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object ThreadLocalUtil {

	class ThreadLocalNonNullDelegate<T> internal constructor(private val defaultValue: T) : ReadWriteProperty<Any?, T> {
		private val threadLocal = ThreadLocal<T>()

		override fun getValue(thisRef: Any?, property: KProperty<*>): T = threadLocal.get() ?: defaultValue

		override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = threadLocal.set(value)
	}

	class ThreadLocalNullableDelegate<T> internal constructor(private val defaultValue: T?) : ReadWriteProperty<Any?, T?> {
		private val threadLocal = ThreadLocal<T>()

		override fun getValue(thisRef: Any?, property: KProperty<*>): T? = threadLocal.get() ?: defaultValue

		override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) = threadLocal.set(value)
	}

	fun <T> delegateNotNull(defaultValue: T) = ThreadLocalNonNullDelegate(defaultValue)


	fun <T> delegate(defaultValue: T? = null) = ThreadLocalNullableDelegate(defaultValue)

}