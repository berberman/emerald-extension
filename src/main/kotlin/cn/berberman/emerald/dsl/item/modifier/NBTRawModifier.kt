package cn.berberman.emerald.dsl.item.modifier

import cn.berberman.emerald.nms.wrapper.nbt.NmsNBTTagCompound
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.util.NmsUtil
import org.bukkit.inventory.ItemStack

class NBTRawModifier(itemStack: ItemStack) : NBTModifier(itemStack) {

	//dsl start
	fun addRawTag(name: String, block: NmsNBTTagCompound.() -> Unit) {
		tag.set(name, NmsNBTTagCompound().apply(block).handle)
	}

	fun getRawTagOrNull(name: String): NmsNBTTagCompound? {
		val instance = tag.get(name) ?: return null
		if (!ReflectionClasses.Nms.NBTTagCompound().isInstance(instance)) return null
		return NmsNBTTagCompound(instance)
	}

	fun removeRawTag(name: String) = tag.remove(name)
	//dsl end

	override fun getResult(): ItemStack = NmsUtil.asBukkitCopy(nms)
}