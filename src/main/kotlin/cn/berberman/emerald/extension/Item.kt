@file:JvmName("ItemStackUtil")


package cn.berberman.emerald.extension

import cn.berberman.emerald.dsl.item.modifier.NBTAttributeModifier
import cn.berberman.emerald.dsl.item.modifier.NBTRawModifier
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta
import org.bukkit.inventory.meta.BookMeta
import org.bukkit.inventory.meta.ItemMeta

/**
 * Operate a bukkit ItemStack's meta.
 * @param block function with receiver to access ItemMeta's class member
 * @return this ItemStack
 */
fun ItemStack.operateMeta(block: ItemMeta.() -> Unit) = apply {
	itemMeta = itemMeta.apply(block)
}

/**
 * Modify a NBT Tag to a bukkit ItemStack.
 * @param block function with receiver to access DSL NBTAttributeTagBuilder
 * @return this ItemStack
 */
fun ItemStack.modifyNBTAttribute(block: NBTAttributeModifier.() -> Unit) = NBTAttributeModifier(this).apply(block).getResult()

fun ItemStack.modifyNBTRaw(block: NBTRawModifier.() -> Unit) = NBTRawModifier(this).apply(block).getResult()

fun ItemStack.operateBookMeta(block: BookMeta.() -> Unit) = apply {
	itemMeta = itemMeta.safeCast<BookMeta>()?.apply(block)
			?: throw IllegalStateException("This is not a book!")
}

fun ItemStack.operateBannerMeta(block: BannerMeta.() -> Unit) = apply {
	itemMeta = itemMeta.safeCast<BannerMeta>()?.apply(block)
			?: throw IllegalStateException("This is not a banner!")
}

fun BookMeta.addPage(vararg baseComponent: BaseComponent) = spigot().addPage(baseComponent)

fun BookMeta.addPage(text: String, block: ComponentBuilder.() -> Unit) = spigot().addPage(componentChat(text, block).create())

fun ItemMeta.addLore(lore: String) = this.lore.add(lore)
