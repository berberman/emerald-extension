package cn.berberman.emerald.extension.dsl.permission

import org.bukkit.permissions.Permission
import org.bukkit.permissions.PermissionDefault

class DSLPermissionBuilder(private val name: String) {

	var description: String = ""

	var defaultValue = PermissionDefault.OP

	private val childPermissionNames = mutableListOf<String>()
	private val childPermission = mutableListOf<Permission>()

	fun childPermission(name: String, block: DSLPermissionBuilder.() -> Unit = {}) {
		var resolveName = name
		if (!resolveName.startsWith(this.name.removeSuffix("*")))
			resolveName = this.name.removeSuffix("*") + name
		childPermissionNames.add(resolveName)
		childPermission.add(DSLPermissionBuilder(resolveName).apply(block).build())
	}

	fun getChildPermissionInstances() = childPermission
	fun build(): Permission {
		val desc = description.takeIf { it.isNotBlank() }
		val map = mutableMapOf<String, Boolean>()
		childPermissionNames.takeIf { it.isNotEmpty() }?.forEach {
			map[it] = true
		}
		return Permission(name, desc, defaultValue, map)
	}
}


class DSLPermissionScope {
	fun permission(name: String, block: DSLPermissionBuilder.() -> Unit = {}) {
		DSLPermissionBuilder(name).apply(block).let(PermissionHolder::addPermission)
	}
}