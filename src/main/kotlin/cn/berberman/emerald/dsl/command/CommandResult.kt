package cn.berberman.emerald.dsl.command

sealed class CommandResult(internal val message: String) {
	internal companion object {
		internal const val COMMAND_NOT_FOUND = "Unknown command. Type \"/help\" for help."
	}

	open class Failed(message: String = "") : CommandResult(message) {
		companion object : Failed()
	}

	internal object SubCommandUnDispatched : CommandResult(COMMAND_NOT_FOUND)

	object Successful : CommandResult("")
}

