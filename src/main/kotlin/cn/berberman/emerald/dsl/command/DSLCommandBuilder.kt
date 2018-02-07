package cn.berberman.emerald.dsl.command

import org.bukkit.command.CommandSender

/**
 * A DSL structure to build commands.
 * @param name command name
 * @author berberman
 */
class DSLCommandBuilder internal constructor(internal val name: String) {
	/**
	 * Read only, which will be invoked when commands execute.
	 */
	var action: Action = { _, _, _ -> false }
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
		action = { seder, _, _ -> block(seder) }
	}

	/**
	 * Set action, retain parameters command sender and command arguments
	 * @param block a function contains the action of execute command,
	 * return boolean represents whether command executes successfully
	 */
	fun action(block: (CommandSender, Array<out String>) -> Boolean) {
		action = { seder, _, args -> block(seder, args) }
	}

	/**
	 * Add alias to this command.
	 * @param alias alias you want's to add
	 */
	fun addAlias(alias: String) = aliases.add(alias)

	/**
	 * A class support function whenSenderIs, holds its data.
	 * @param senderInstance sender
	 * @param isTarget whether sender is target object
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
		 * If don't use otherwise, you have to use invoke operator to get final result.
		 * @deprecated Not recommended, use [otherwise] instead.
		 * @see otherwise
		 */
		@Deprecated("Not recommended", ReplaceWith("otherwise"))
		operator fun invoke() = result
	}

	/**
	 * Provide a function to take place of ` if...else... `
	 * @param T target type that assess whether sender is
	 * @param sender command sender
	 * @param block action if sender is target object
	 */
	inline fun <reified T : CommandSender> whenSenderIs(sender: CommandSender, block: T.() -> Boolean) =
			(sender is T).let { isTarget ->
				TargetAndSenderBlocksData(sender, isTarget, if (isTarget)
					(sender as T).block() else false)
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
 * A DSL structure to build command builder.
 * @author berberman
 */
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
					permissionMessage, before, after).let(CommandHolder::add)
		}
	}

}

		/**
		 * (CommandSender, String, Array<out String>) -> Boolean is too long,
		 * use typealias to replace :)
		 */
typealias Action = (CommandSender, String, Array<out String>) -> Boolean