package cn.berberman.emerald.extension.dsl.command

import cn.berberman.emerald.extension.Emerald
import cn.berberman.emerald.extension.extension.logger
import cn.berberman.emerald.extension.extension.sendMessage
import cn.berberman.emerald.extension.extension.times
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class PackingCommand
(name: String,
 description: String,
 usageMessage: String,
 aliases: List<String>,
 private val action: (CommandSender, String, Array<out String>) -> Boolean,
 permission: String,
 permissionMessage: String,
 private val before: (CommandSender, String) -> Unit,
 private val after: (CommandSender, String) -> Unit
) : Command(name,
		description,
		usageMessage,
		aliases) {
	init {
		if (permissionMessage.isNotBlank())
			super.setPermissionMessage(permissionMessage)
		if (permission.isNotBlank())
			super.setPermission(permission)
	}

	override fun execute(p0: CommandSender, p1: String, p: Array<out String>): Boolean {
		before(p0, p1)
		return action(p0, p1, p).apply {
			if (!this) {
				p0 sendMessage ChatColor.RED * "命令执行错误"
				if (Emerald.debug)
					logger.info("命令:/$p1 执行错误!")
			}
			after(p0, p1)
		}
	}
}