package cn.berberman.emerald.dsl.permission

import cn.berberman.emerald.extension.debug
import cn.berberman.emerald.util.EmeraldUtil.pluginManager

/**
 * A object holds permissions we want's to registerPlugin.
 * @see PermissionsBuilderDsl
 * @author berberman
 */
internal object PermissionHolder {
	private val permissionList = mutableListOf<PermissionBuilderDsl>()


	internal fun addPermission(permissionBuilderDsl: PermissionBuilderDsl) = permissionList.add(permissionBuilderDsl)


	internal fun register() {
		permissionList.flatMap { it.getChildPermissionInstances() }.forEach(pluginManager::addPermission)
		permissionList.flatMap { it.getChildPermissionInstances() }.forEach {
			debug("registerPlugin child permission: ${it.name}")
		}
		permissionList.forEach { it.build().let(pluginManager::addPermission) }
		permissionList.forEach {
			it.build().let {
				debug("registerPlugin permission: ${it.name}")
			}
		}
	}
}