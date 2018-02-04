package cn.berberman.emerald.extension.dsl.command

import org.bukkit.command.CommandSender

class DSLCommandBuilder(val name: String) {
	var action: (CommandSender, String, Array<out String>) -> Boolean = { _, _, _ -> false }
		private set
	var description: String = ""
	var usageMessage: String = ""
	val aliases: MutableList<String> = mutableListOf()
	var permission: String = ""
	var permissionMessage: String = ""

	fun action(block: (CommandSender) -> Boolean) {
		action = { seder, _, _ -> block(seder) }
	}

	fun action(block: (CommandSender, Array<out String>) -> Boolean) {
		action = { seder, _, args -> block(seder, args) }
	}

	fun addAlias(alias: String) = aliases.add(alias)

	class TargetAndSenderBlocksData
	(private val senderInstance: CommandSender,
	 private val isTarget: Boolean,
	 private val result: Boolean) {
		infix fun otherwise(block: CommandSender.() -> Boolean) =
				if (!isTarget) senderInstance.block() else result

		operator fun invoke() = result
	}

	inline fun <reified T : CommandSender> whenSenderIs(sender: CommandSender, block: T.() -> Boolean) =
			(sender is T).let { isTarget ->
				TargetAndSenderBlocksData(sender, isTarget, if (isTarget)
					(sender as T).block() else false)
			}

}

internal fun buildCommands(block: DSLCommandScope.() -> Unit) {
	DSLCommandScope().apply(block)
}

class DSLCommandScope {


	var before: (CommandSender, String) -> Unit = { _, _ -> }
		private set
	var after: (CommandSender, String) -> Unit = { _, _ -> }
		private set

	fun before(block: (CommandSender, String) -> Unit) {
		before = block
	}

	fun after(block: (CommandSender, String) -> Unit) {
		after = block
	}

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