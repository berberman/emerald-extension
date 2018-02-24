package cn.berberman.emerald.dsl.permission

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.info
import cn.berberman.emerald.util.EmeraldUtil.pluginManager

/**
 * A object holds permissions we want's to register.
 * @see DSLPermissionScope
 * @author berberman
 */
internal object PermissionHolder {
	private val simplePermissionList = mutableListOf<DSLPermissionBuilder>()


	internal fun addPermission(permissionBuilder: DSLPermissionBuilder) = simplePermissionList.add(permissionBuilder)


	internal fun register() {
		simplePermissionList.flatMap { it.getChildPermissionInstances() }.forEach(pluginManager::addPermission)
		simplePermissionList.flatMap { it.getChildPermissionInstances() }.forEach {
			if (Emerald.debug)
				info("register child permission: ${it.name}")
		}
		simplePermissionList.forEach { it.build().let(pluginManager::addPermission) }
		simplePermissionList.forEach {
			it.build().let {
				if (Emerald.debug)
					info("register permission: ${it.name}")
			}
		}
	}
}