package cn.berberman.emerald.extension.dsl.command

import cn.berberman.emerald.extension.Emerald
import cn.berberman.emerald.extension.extension.logger
import cn.berberman.emerald.extension.extension.plugin
import org.bukkit.command.Command
import org.bukkit.command.CommandMap

object CommandHolder {
	private val commands = mutableListOf<PackingCommand>()

	fun add(packingCommand: PackingCommand) = commands.add(packingCommand)

	fun register(commandMap: CommandMap) {
		commands.forEach {
			commandMap.register(plugin.name, it as Command)
			if (Emerald.debug)
				logger.info("注册命令：/${it.name}")
		}
	}
}