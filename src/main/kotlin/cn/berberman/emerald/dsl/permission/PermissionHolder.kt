package cn.berberman.emerald.dsl.permission

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.logger
import cn.berberman.emerald.extension.pluginManager

/**
 * A object holds permissions we want's to register.
 * @see DSLPermissionScope
 * @author berberman
 */
internal object PermissionHolder {
	private val simplePermissionList = mutableListOf<DSLPermissionBuilder>()


	fun addPermission(permissionBuilder: DSLPermissionBuilder) = simplePermissionList.add(permissionBuilder)


	internal fun register() {
		simplePermissionList.flatMap { it.getChildPermissionInstances() }.forEach(pluginManager::addPermission)
		simplePermissionList.flatMap { it.getChildPermissionInstances() }.forEach {
			if (Emerald.debug)
				logger.info("注册子权限：${it.name}")
		}
		simplePermissionList.forEach { it.build().let(pluginManager::addPermission) }
		simplePermissionList.forEach {
			it.build().let {
				if (Emerald.debug)
					logger.info("注册权限：${it.name}")
			}
		}
	}
}