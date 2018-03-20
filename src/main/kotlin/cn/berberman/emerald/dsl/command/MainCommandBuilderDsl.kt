package cn.berberman.emerald.dsl.command

import cn.berberman.emerald.dsl.annotation.CommandBuilderMarker
import org.bukkit.command.CommandSender

/**
 * A DSL structure to build commands.
 * @param name command name
 * @author berberman
 */
@CommandBuilderMarker
class DslCommandBuilder internal constructor(internal val name: String) {
	private val subCommands =
			mutableMapOf<String, SubCommandBuilderDsl>()
	//dsl start
	/**
	 * Read only, which will be invoked when commands execute.
	 */
	var action: Action = { sender, _, args ->
		dispatchSubCommand(sender, args).let { result ->
			if (result === CommandResult.SubCommandUnDispatched)
				CommandResult.Failed(CommandResult.COMMAND_NOT_FOUND)
			else result
		}
	}
		private set
	/**
	 * The description of command, default is empty.
	 */
	var description: String = ""
	/**
	 * The usageMessage of command, default is empty.
	 */
	var usageMessage: String = ""
	/**
	 * The aliases of command, default is empty.
	 */
	val aliases: MutableList<String> = mutableListOf()
	/**
	 * The permission that this command needs, default is empty.
	 */
	var permission: String = ""
	/**
	 * The message will say if invoker doesn't have permission, default is empty.
	 */
	var permissionMessage: String = ""

	/**
	 * Set action, only retain parameter command sender
	 * @param block a function contains the action of execute command,
	 * return boolean represents whether command executes successfully
	 */
	fun action(block: (CommandSender) -> CommandResult) {
		action = { sender, _, args ->
			dispatchSubCommand(sender, args).let { result ->
				if (result === CommandResult.SubCommandUnDispatched && args.isEmpty()) block(sender)
//				else if (result === CommandResult.SubCommandUnDispatched && args.isNotEmpty())
//					CommandResult.Failed(CommandResult.COMMAND_NOT_FOUND)
				else result
			}
		}
	}

	/**
	 * Set action, retain parameters command sender and command arguments
	 * @param block a function contains the action of execute command,
	 * return boolean represents whether command executes successfully
	 */
	fun action(block: (CommandSender, Array<out String>) -> CommandResult) {
		action = { sender, _, args ->
			dispatchSubCommand(sender, args).let { result ->
				if (result === CommandResult.SubCommandUnDispatched && args.isEmpty()) block(sender, args)
//				else if (result === CommandResult.SubCommandUnDispatched && args.isNotEmpty())
//					CommandResult.Failed(CommandResult.COMMAND_NOT_FOUND)
				else result
			}
		}
	}

	/**
	 * Create a sub-command
	 *
	 * sub-commands will be invoked when the first argument equals its name.
	 * In this case, super action will be ignored.
	 *
	 * @param name sub-command name
	 * @param block action to invoke when sub-command dispatch
	 *      Args supplied to sub-command will be removed first element(sub-command name).
	 *
	 */
	fun subCommand(name: String, block: SubCommandBuilderDsl.() -> Unit) {
		if (subCommands.containsKey(name)) throw IllegalArgumentException("SubCommand Already Existed")
		subCommands[name] = SubCommandBuilderDsl(name).apply(block)
	}

	/**
	 * Add alias to this command.
	 * @param alias alias you want's to add
	 */
	fun addAlias(alias: String) = aliases.add(alias)
	//dsl end

	/**
	 * A class support function whenSenderIs, holds its data.
	 * @param senderInstance sender
	 * @param isTarget whether sender is target type
	 * @param result if it is, the result of execute
	 * @author berberman
	 * @see whenSenderIs
	 */
	class TargetAndSenderBlocksData
	(private val senderInstance: CommandSender,
	 private val isTarget: Boolean,
	 private val result: CommandResult) {
		/**
		 * Set otherwise action and return final result.
		 * @param block the action that if sender ins't target object
		 */
		infix fun otherwise(block: CommandSender.() -> CommandResult) =
				if (!isTarget) senderInstance.block() else result

		/**
		 * If you don't use otherwise, you have to use invoke operator to get final result.
		 * @deprecated Not recommended, use [otherwise] instead.
		 * @see otherwise
		 */
		@Deprecated("Not recommended", ReplaceWith("otherwise"))
		operator fun invoke() = result
	}

	/**
	 * Provide a function to take place of ` if...else... `
	 *    *infix fun can't declared generic explicitly*
	 * @param T type that assess whether sender is
	 * @receiver sender command sender
	 * @param block action if sender is target type
	 */
	inline infix fun <reified T : CommandSender>
			CommandSender.whenSenderIs(block: T.() -> CommandResult) =
			(this is T).let { isTarget ->
				TargetAndSenderBlocksData(this, isTarget, if (isTarget)
					(this as T).block() else CommandResult.Failed())
			}

	private fun dispatchSubCommand(sender: CommandSender, args: Array<out String>): CommandResult =
			if (args.isEmpty()) CommandResult.SubCommandUnDispatched
			else subCommands[args[0]]?.action?.invoke(sender, mutableListOf<String>()
					.apply {
						addAll(args)
						remove(args[0])
					}.toTypedArray()) ?: CommandResult.SubCommandUnDispatched

	internal val defaultProcessTabComplete: PackingTabCompleter.(CommandSender, Array<out String>) -> Unit = { _, args ->
		subCommands.keys.filter { it.startsWith(args.last(), true) }.let(this::addAll)
		sort()
	}

//	private enum class SubCommandInvokeState(val value: Boolean?) {
//		SUCCESSFUL(true),
//		FAILED(false),
//		UN_DISPATCHED(null);
//
//		internal companion object {
//			fun valueOf(value: Boolean?) = when (value) {
//				true -> SUCCESSFUL
//				false -> FAILED
//				null -> UN_DISPATCHED
//			}
//		}
//	}
}