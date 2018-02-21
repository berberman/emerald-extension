package cn.berberman.emerald.dsl.command

import cn.berberman.emerald.extension.CommonBuilder
import org.bukkit.command.CommandSender

/**
 * A DSL structure to build commands.
 * @param name command name
 * @author berberman
 */
@CommandBuilder
class DSLCommandBuilder internal constructor(internal val name: String) {
	private val subCommands =
			mutableMapOf<String, DSLSubCommandBuilder>()
	/**
	 * Read only, which will be invoked when commands execute.
	 */
	var action: Action = { sender, _, args -> dispatchSubCommand(sender, args).value ?: true }
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
	fun action(block: (CommandSender) -> Boolean) {
		action = { sender, _, args ->
			dispatchSubCommand(sender, args).let { result ->
				if (result == SubCommandInvokeState.UN_DISPATCHED && args.isEmpty()) block(sender)
				else if (result == SubCommandInvokeState.UN_DISPATCHED && args.isNotEmpty()) false
				else result.value ?: throw IllegalStateException("That's impossible")
			}
		}
	}

	/**
	 * Set action, retain parameters command sender and command arguments
	 * @param block a function contains the action of execute command,
	 * return boolean represents whether command executes successfully
	 */
	fun action(block: (CommandSender, Array<out String>) -> Boolean) {
		action = { sender, _, args ->
			dispatchSubCommand(sender, args).let { result ->
				if (result == SubCommandInvokeState.UN_DISPATCHED && args.isNotEmpty()) block(sender, args)
				else if (result == SubCommandInvokeState.UN_DISPATCHED && args.isNotEmpty()) false
				else result.value ?: throw IllegalStateException("That's impossible")
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
	fun subCommand(name: String, block: DSLSubCommandBuilder.() -> Unit) {
		subCommands[name] = DSLSubCommandBuilder(name).apply(block)
	}

	/**
	 * Add alias to this command.
	 * @param alias alias you want's to add
	 */
	fun addAlias(alias: String) = aliases.add(alias)

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
	 private val result: Boolean) {
		/**
		 * Set otherwise action and return final result.
		 * @param block the action that if sender ins't target object
		 */
		infix fun otherwise(block: CommandSender.() -> Boolean) =
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
			CommandSender.whenSenderIs(block: T.() -> Boolean) =
			(this is T).let { isTarget ->
				TargetAndSenderBlocksData(this, isTarget, if (isTarget)
					(this as T).block() else false)
			}

	private fun dispatchSubCommand(sender: CommandSender, args: Array<out String>): SubCommandInvokeState =
			if (args.isEmpty()) SubCommandInvokeState.UN_DISPATCHED
			else subCommands[args[0]]?.action?.invoke(sender, mutableListOf<String>()
					.apply {
						addAll(args)
						remove(args[0])
					}.toTypedArray()).let { SubCommandInvokeState.valueOf(it) }

	internal val defaultProcessTabComplete: PackingTabCompleter.(CommandSender, Array<out String>) -> Unit = { _, args ->
		subCommands.keys.filter { it.startsWith(args.last(), true) }.let(this::addAll)
		sort()
	}

	private enum class SubCommandInvokeState(val value: Boolean?) {
		SUCCESSFUL(true),
		FAILED(false),
		UN_DISPATCHED(null);

		internal companion object {
			fun valueOf(value: Boolean?) = when (value) {
				true  -> SUCCESSFUL
				false -> FAILED
				null  -> UN_DISPATCHED
			}
		}
	}
}

/**
 * Provide Emerald to access that, build a DSL command builder,
 * @param block DSL structure
 */
internal fun buildCommands(block: DSLCommandScope.() -> Unit) {
	DSLCommandScope().apply(block)
}

/**
 * A DSL structure to build sub command.
 * @author berberman
 */
@SubCommandBuilder
class DSLSubCommandBuilder internal constructor(internal val name: String) {
	/**
	 * sub command action, read only.
	 */
	var action: (CommandSender, Array<out String>) -> Boolean = { _, _ -> true }
		private set

	/**
	 * set sub command action
	 * @param block action
	 */
	fun action(block: (CommandSender) -> Boolean) {
		action = { sender, _ -> block(sender) }
	}

	/**
	 * set sub command action
	 * @param block action
	 */
	fun action(block: (CommandSender, Array<out String>) -> Boolean) {
		action = block
	}
}

/**
 * A DSL structure to build command builder.
 * @author berberman
 */
@CommonBuilder
class DSLCommandScope internal constructor() {

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
	fun command(name: String, block: DSLCommandBuilder.() -> Unit) {
		DSLCommandBuilder(name).apply(block).apply {
			PackingCommand(this.name,
					description,
					usageMessage,
					aliases,
					action,
					permission,
					permissionMessage, before, after, PackingTabCompleter(defaultProcessTabComplete)).let(CommandHolder::add)
		}
	}

}

		/**
		 * (CommandSender, String, Array<out String>) -> Boolean is too long,
		 * use typealias to replace :)
		 */
typealias Action = (CommandSender, String, Array<out String>) -> Boolean

@DslMarker
internal annotation class CommandBuilder

@DslMarker
internal annotation class SubCommandBuilder