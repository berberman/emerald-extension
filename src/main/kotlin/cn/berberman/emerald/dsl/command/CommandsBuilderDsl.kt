package cn.berberman.emerald.dsl.command

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.dsl.annotation.CommonBuilderMarker
import cn.berberman.emerald.extension.info
import cn.berberman.emerald.util.EmeraldUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender


/**
 * Provide Emerald to access that, build a DSL command builder,
 * @param block DSL structure
 */
fun commands(block: CommandsBuilder.() -> Unit) =
		CommandsBuilder().apply(block)


/**
 * A DSL structure to build command builder.
 * @author berberman
 */
@CommonBuilderMarker
class CommandsBuilder internal constructor() {

	private val commands = mutableSetOf<PackingCommand>()

	//dsl start
	/**
	 * Action before execute, default is empty.Read only.
	 */
	var before: (CommandSender, String) -> Unit = { _, _ -> }
		private set
	/**
	 * Action after execute, default is empty.Read only.
	 */
	var after: (CommandSender, String) -> Unit = { _, _ -> }
		private set

	/**
	 * Set property before
	 * @param block action before execute
	 * @see [before] property
	 */
	fun before(block: (CommandSender, String) -> Unit) {
		before = block
	}

	/**
	 * Set property after
	 * @param block action after execute
	 * @see [after] property
	 */
	fun after(block: (CommandSender, String) -> Unit) {
		after = block
	}

	/**
	 * Build a command
	 * @param name command name
	 * @param block other information about command, a DSL structure
	 */
	fun command(name: String, block: DslCommandBuilder.() -> Unit) {
		DslCommandBuilder(name).apply(block).apply {
			PackingCommand(this.name,
					description,
					usageMessage,
					aliases,
					action,
					permission,
					permissionMessage, before, after, PackingTabCompleter(defaultProcessTabComplete))
					.takeUnless { commands.any { e -> e.name == it.name } }?.let(commands::add)
					?: throw IllegalArgumentException("Command Already Existed")

		}
	}
	//dsl end

	internal fun register(commandMap: CommandMap) {
		commands.forEach {
			commandMap.register(EmeraldUtil.plugin.name, it as Command)
			if (Emerald.debug)
				info("registerPlugin command: /${it.name}")
		}
	}
}

/**
 * (CommandSender, String, Array<out String>) -> Boolean is too long,
 * use typealias to replace :)
 */
typealias Action = (CommandSender, String, Array<out String>) -> CommandResult


/**
 * Register commands.
 * @param block DSL part of building commands.
 */
fun registerCommands(block: CommandsBuilder.() -> Unit) =
		commands(block).register()


fun CommandsBuilder.register() =
		register(EmeraldUtil.commandMap)


