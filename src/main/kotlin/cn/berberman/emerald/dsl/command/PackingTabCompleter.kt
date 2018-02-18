package cn.berberman.emerald.dsl.command

import org.bukkit.command.CommandSender

class PackingTabCompleter {
	lateinit var sender: CommandSender
		internal set
	lateinit var args: Array<out String>
		internal set
	internal var childPackingTabCompleter: (PackingTabCompleter.() -> Unit)? = null
	val result = mutableListOf<String>()

	internal fun onComplete(sender: CommandSender, args: Array<out String>) {
		this.args = args
		this.sender = sender
	}

	fun lastWord() = args.last()

	fun String.startWithIgnoreCase(prefix: String) = startsWith(prefix, true)

	fun sort() = result.sort()

	internal fun cleanUp() {
		childPackingTabCompleter = null
		result.clear()
	}

}