package cn.berberman.emerald.javassist
@Deprecated("Un reached",level = DeprecationLevel.HIDDEN)
class DSLMethodSrcBuilder {
	private val builder = StringBuilder()
	fun appendln(text: String) = builder.appendln(text)
	fun append(text: String) = builder.append(text)
	fun invokeFunction(name: String) = appendln("$name($$);")

	fun build() = builder.toString()
}
//@Deprecated("Un reached",level = DeprecationLevel.HIDDEN)
//fun buildMethod(builderAction: DSLMethodSrcBuilder.() -> Unit) =
//		DSLMethodSrcBuilder().apply(builderAction).build()