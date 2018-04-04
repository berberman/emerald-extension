package cn.berberman.emerald.dsl.item.modifier

import cn.berberman.emerald.dsl.item.tag.NBTAttributeTagBuilder
import cn.berberman.emerald.nms.wrapper.nbt.NmsNBTTagCompound
import cn.berberman.emerald.nms.wrapper.nbt.NmsNBTTagList
import cn.berberman.emerald.reflection.ReflectionClasses
import cn.berberman.emerald.util.NmsUtil
import cn.berberman.emerald.util.ReflectionUtil
import org.bukkit.inventory.ItemStack

/**
 * NBT Modifier, let NBTAttributeTagBuilder's value add to ItemStack.
 * @see NBTAttributeTagBuilder
 * @author berberman
 * @param itemStack bukkit ItemStack
 */
class NBTAttributeModifier(itemStack: ItemStack) : NBTModifier(itemStack) {

	//dsl start
	/**
	 * Let outer class to access modifier.
	 */
	fun addAttributeTag(block: NBTAttributeTagBuilder.() -> Unit) = operateList {
		add(NBTAttributeTagBuilder().apply(block).nbtTagCompound.handle)
	}

	fun removeAttributeTagByIndex(index: Int) = operateList {
		remove(index)
	}

	private fun removeAttributeByString(key: String) = operateList {
		getInternal().map(::NmsNBTTagCompound).filter {
			val value = it.get(key) ?: return@filter false
			ReflectionUtil.getField<String>(ReflectionClasses.NmsClass.NBTTagString(),
					"data", value) == key
		}.let {
			getInternal().removeAll(it.map(NmsNBTTagCompound::handle))
		}
	}

	fun removeAttributeTagByType(type: NBTAttributeTagBuilder.NBTType) =
			removeAttributeByString(type.getNBTName())

	fun removeAttributeTagByName(name: String) =
			removeAttributeByString(name)

	fun clearAllAttributeTags() = operateList { internalClear() }
	//dsl end

	private fun operateList(block: NmsNBTTagList.() -> Unit) =
			tag.set("AttributeModifiers", getOrNewList().apply(block).handle)

	private fun getOrNewList() = tag.get("AttributeModifiers")?.let { NmsNBTTagList(it) }
			?: NmsNBTTagList()

	override fun getResult() = NmsUtil.asBukkitCopy(nms.apply { setTag(this@NBTAttributeModifier.tag) })


}

