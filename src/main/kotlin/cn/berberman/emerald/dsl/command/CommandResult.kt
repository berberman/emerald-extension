package cn.berberman.emerald.dsl.command

sealed class CommandResult(internal val message: String) {
	internal companion object {
		internal const val COMMAND_NOT_FOUND = "Unable to find this command!"
	}

	open class Failed(message: String = "") : CommandResult(message)

	internal object SubCommandUnDispatched : CommandResult(COMMAND_NOT_FOUND)

	object Successful : CommandResult("")
}

