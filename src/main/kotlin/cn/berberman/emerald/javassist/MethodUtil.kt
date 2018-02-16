package cn.berberman.emerald.javassist

import cn.berberman.emerald.extension.plugin
import javassist.ClassPool
import javassist.LoaderClassPath
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaMethod

@Deprecated("Un reached",level = DeprecationLevel.HIDDEN)
object MethodUtil {
	private fun getPluginClassLoader(){
	}
	private val classPool = ClassPool.getDefault().apply {
		insertClassPath(LoaderClassPath(plugin::class.java.classLoader))
	}

	fun insertAfter(method: KFunction<*>, src: String):Class<*> {
		val clazz = classPool[method.javaMethod?.declaringClass?.name!!]
		val m = clazz.getDeclaredMethod(method.name)
		m.insertAfter(src)
		return clazz.toClass()
	}
}