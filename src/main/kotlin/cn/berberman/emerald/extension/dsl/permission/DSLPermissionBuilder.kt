package cn.berberman.emerald.extension.dsl.permission

import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

/**
 *  A DSL structure to build permissions.
 *  @author berberman
 *  @see org.bukkit.permissions.Permission
 */
class DSLPermissionBuilder internal constructor(private val name: String) {
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
	fun childPermission(name: String, block: DSLPermissionBuilder.() -> Unit = {}) {
		var resolveName = name
		if (!resolveName.startsWith(this.name.removeSuffix("*")))
			resolveName = this.name.removeSuffix("*") + name
		childPermissionNames.add(resolveName)
		childPermission.add(DSLPermissionBuilder(resolveName).apply(block).build())
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
 * @see DSLPermissionBuilder
 */
class DSLPermissionScope internal constructor() {
	/**
	 * Build a permission.
	 * @param name permission name
	 * @param block DSL child permission builder, default is `{}`
	 */
	fun permission(name: String, block: DSLPermissionBuilder.() -> Unit = {}) {
		DSLPermissionBuilder(name).apply(block).let(PermissionHolder::addPermission)
	}
}