package cn.berberman.emerald.nms.wrapper.meta

import cn.berberman.emerald.extension.toIChatBaseList
import cn.berberman.emerald.nms.wrapper.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.nms.wrapper.ReflectionWrapper
import cn.berberman.emerald.reflection.getFieldAccess
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.inventory.meta.BookMeta

class BukkitCraftMetaBook(meta: BookMeta) : ReflectionWrapper(), BookMeta by meta {
	override val clazz: Class<*> = ReflectionClasses.CraftBukkitClass.CraftMetaBook()

	override val handle: Any = meta

	@Suppress("UNCHECKED_CAST")
	private val pages: MutableList<Any> = clazz.getFieldAccess()[handle, "pages"] as MutableList<Any>

	fun addPages(vararg text: NmsIChatBaseComponent) = /*text.forEach { pages.add(it.nmsChat) }*/
			pages.addAll(text.map(NmsIChatBaseComponent::nmsChat))

	fun addPage(text: Array<BaseComponent>) = addPages(*text.toIChatBaseList().toTypedArray())
}