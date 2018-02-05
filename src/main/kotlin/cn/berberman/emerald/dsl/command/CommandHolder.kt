package cn.berberman.emerald.dsl.command

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.logger
import cn.berberman.emerald.extension.plugin
import org.bukkit.command.Command
import org.bukkit.command.CommandMap

/**
 * A object holds commands we want's to register.
 * @see DSLCommandBuilder
 * @author berberman
 */
internal object CommandHolder {
	private val commands = mutableListOf<PackingCommand>()

	/**
	 * Add a command to register list.
	 * @param packingCommand a command to register
	 */
	fun add(packingCommand: PackingCommand) = commands.add(packingCommand)

	/**
	 * Register all commands, you don't have to call it.
	 * @param commandMap server's command map
	 */
	fun register(commandMap: CommandMap) {
		commands.forEach {
			commandMap.register(plugin.name, it as Command)
			if (Emerald.debug)
				logger.info("注册命令：/${it.name}")
		}
	}
}