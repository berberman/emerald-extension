package cn.berberman.emerald.nms.wrapper.meta

import cn.berberman.emerald.extension.toIChatBaseList
import cn.berberman.emerald.nms.wrapper.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.reflection.ReflectionWrapper
import cn.berberman.emerald.reflection.getFieldAccess
import cn.berberman.emerald.util.NmsUtil
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.inventory.meta.BookMeta

@Deprecated("Low performance", ReplaceWith("BookMeta##spigot()", "org.bukkit.inventory.meta.BookMeta"))
class BukkitCraftMetaBook(meta: BookMeta) : ReflectionWrapper(), BookMeta by meta {
	override val clazz: Class<*> = NmsUtil.getCraftBukkitClass("inventory.CraftMetaBook")

	override val instance: Any = meta

	@Suppress("UNCHECKED_CAST")
	private val pages: MutableList<Any> = clazz.getFieldAccess()[instance, "pages"] as MutableList<Any>

	fun addPages(vararg text: NmsIChatBaseComponent) = /*text.forEach { pages.add(it.nmsChat) }*/
			pages.addAll(text.map(NmsIChatBaseComponent::nmsChat))

	fun addPage(text: Array<BaseComponent>) = addPages(*text.toIChatBaseList().toTypedArray())
}