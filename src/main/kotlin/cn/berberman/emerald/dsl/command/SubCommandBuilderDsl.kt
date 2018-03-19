package cn.berberman.emerald.dsl.command

import cn.berberman.emerald.dsl.annotation.SubCommandBuilderMarker
import org.bukkit.command.CommandSender

/**
 * A DSL structure to build sub command.
 * @author berberman
 */
@SubCommandBuilderMarker
class SubCommandBuilderDsl internal constructor(internal val name: String) {
	/**
	 * sub command action, read only.
	 */
	var action: (CommandSender, Array<out String>) -> CommandResult = { _, _ -> CommandResult.Successful }
		private set

	/**
	 * set sub command action
	 * @param block action
	 */
	fun action(block: (CommandSender) -> CommandResult) {
		action = { sender, _ -> block(sender) }
	}

	/**
	 * set sub command action
	 * @param block action
	 */
	fun action(block: (CommandSender, Array<out String>) -> CommandResult) {
		action = block
	}
}
