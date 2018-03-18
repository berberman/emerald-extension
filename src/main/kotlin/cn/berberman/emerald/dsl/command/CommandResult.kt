package cn.berberman.emerald.dsl.command

data class CommandResult(internal val result: Boolean, internal val message: String) {

	companion object {
		val SUCCESS = CommandResult(true, "")
		val EMPTY_FAIL = CommandResult(false, "")
	}

	operator fun plus(another: CommandResult) = CommandResult(result && another.result,
			{ aBlank: Boolean, bBlank: Boolean ->
				when {
					!aBlank && bBlank  -> message
					aBlank && !bBlank  -> another.message
					!aBlank && !bBlank -> "$message||${another.message}"
					else               -> ""
				}
			}(message.isBlank(), another.message.isBlank()))
}