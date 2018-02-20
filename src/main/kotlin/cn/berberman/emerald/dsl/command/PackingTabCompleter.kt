package cn.berberman.emerald.dsl.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class PackingTabCompleter(private val action: MutableList<String>.(CommandSender, Array<out String>) -> Unit) : TabCompleter {


	override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>)
			: List<String> {
		return mutableListOf<String>().apply { action(sender, args) }
	}
}
