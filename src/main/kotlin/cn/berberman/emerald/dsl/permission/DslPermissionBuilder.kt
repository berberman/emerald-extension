package cn.berberman.emerald.dsl.permission

import cn.berberman.emerald.dsl.CommonBuilderMarker
import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

/**
 *  A DSL structure to build permissions.
 *  @author berberman
 */
@PermissionBuilder
class DslPermissionBuilder internal constructor(private val name: String) {
	/**
	 * the description of permission, default is empty.
	 */
	var description: String = ""


	/**
	 * the defaultValue of permission, default is OP.
	 */
	var defaultValue = PermissionDefault.OP

	private val childPermissionNames = mutableListOf<String>()
	private val childPermission = mutableListOf<Permission>()
	/**
	 * Build a child permission
	 * @param name child permission name
	 * @param block DSL child permission builder, default is `{}`
	 */
	fun childPermission(name: String, block: DslPermissionBuilder.() -> Unit = {}) {
		var resolveName = name
		if (!resolveName.startsWith(this.name.removeSuffix("*")))
			resolveName = this.name.removeSuffix("*") + name
		childPermissionNames.add(resolveName)
		childPermission.add(DslPermissionBuilder(resolveName).apply(block).build())
	}

	internal fun getChildPermissionInstances() = childPermission
	internal fun build(): Permission {
		val desc = description.takeIf { it.isNotBlank() }
		val map = mutableMapOf<String, Boolean>()
		childPermissionNames.takeIf { it.isNotEmpty() }?.forEach {
			map[it] = true
		}
		return Permission(name, desc, defaultValue, map)
	}
}

/**
 * A DSL structure to build permission builder.
 * @author berberman
 * @see DslPermissionBuilder
 */
@CommonBuilderMarker
class DslPermissionScope internal constructor() {
	/**
	 * Build a permission.
	 * @param name permission name
	 * @param block DSL child permission builder, default is `{}`
	 */
	fun permission(name: String, block: DslPermissionBuilder.() -> Unit = {}) {
		DslPermissionBuilder(name).apply(block).let(PermissionHolder::addPermission)
	}
}

@DslMarker
internal annotation class PermissionBuilder

/**
 * Register permissions.
 * @param block  DSL part of building permissions.
 */
fun registerPermissions(block: DslPermissionScope.() -> Unit) {
	DslPermissionScope().block()
	PermissionHolder.register()
}