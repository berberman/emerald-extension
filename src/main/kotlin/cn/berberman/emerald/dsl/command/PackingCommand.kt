package cn.berberman.emerald.dsl.command

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.logger
import cn.berberman.emerald.extension.sendMessage
import cn.berberman.emerald.extension.times
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

/**
 * Packing bukkit command, override its method [execute]
 * @param name command name
 * @param description command description
 * @param usageMessage command usageMessage
 * @param aliases command aliases
 * @param action action invoked when execute command
 * @param permission permission command needs
 * @param permissionMessage message to say if sender doesn't have permission
 * @param before action before execute
 * @param after action after execute
 * @author berberman
 * @see Command
 * @see Command#execute
 */
internal class PackingCommand
(name: String,
 description: String,
 usageMessage: String,
 aliases: List<String>,
 private val action: Action,
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

	/**
	 * Override execute method, invoke actions.
	 * This method will be invoked by Server.
	 * @param p0 sender
	 * @param p1 command name
	 * @param p command parameters
	 */
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