package cn.berberman.emerald.dsl.item.modifier

import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.nms.wrapper.nbt.NmsNBTTagCompound
import org.bukkit.inventory.ItemStack


abstract class NBTModifier(itemStack: ItemStack) {
	protected val nms = NmsItemStack(itemStack)

	protected val tag: NmsNBTTagCompound

	init {
		tag = if (nms.hasTag()) nms.getTag() else NmsNBTTagCompound()
	}

	internal abstract fun getResult(): ItemStack
}