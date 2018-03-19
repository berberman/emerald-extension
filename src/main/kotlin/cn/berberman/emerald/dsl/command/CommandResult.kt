package cn.berberman.emerald.dsl.command

sealed class CommandResult(internal val result: Boolean, internal val message: String) {

	class Failed(message: String = "") : CommandResult(false, message)

	object Successful : CommandResult(true, "")
}

