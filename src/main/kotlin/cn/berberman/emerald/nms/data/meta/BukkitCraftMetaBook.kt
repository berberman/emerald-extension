package cn.berberman.emerald.nms.data.meta

import cn.berberman.emerald.extension.getFieldAccess
import cn.berberman.emerald.extension.toIChatBaseList
import cn.berberman.emerald.nms.NmsReflection
import cn.berberman.emerald.nms.data.chat.interfaces.NmsIChatBaseComponent
import cn.berberman.emerald.util.NmsUtil
import net.md_5.bungee.api.chat.BaseComponent
import org.bukkit.inventory.meta.BookMeta

class BukkitCraftMetaBook(meta: BookMeta) : NmsReflection(), BookMeta by meta {
	override val targetNMSClass: Class<*> = NmsUtil.getCraftBukkitClass("inventory.CraftMetaBook")

	override val instanceNMS: Any = meta

	@Suppress("UNCHECKED_CAST")
	private val pages: MutableList<Any> = targetNMSClass.getFieldAccess()[instanceNMS, "pages"] as MutableList<Any>

	fun addPages(vararg text: NmsIChatBaseComponent) = /*text.forEach { pages.add(it.nmsChat) }*/
			pages.addAll(text.map { it.nmsChat })

	fun addPage(text: Array<BaseComponent>) = addPages(*text.toIChatBaseList().toTypedArray())
}