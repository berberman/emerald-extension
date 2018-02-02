package cn.berberman.emerald.extension

import net.minecraft.server.v1_12_R1.*
import org.apache.commons.lang3.RandomUtils
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun ItemStack.convertToNNS(): net.minecraft.server.v1_12_R1.ItemStack = CraftItemStack.asNMSCopy(this)

fun net.minecraft.server.v1_12_R1.ItemStack.convertToBukkit(): ItemStack = CraftItemStack.asBukkitCopy(this)

fun ItemStack.opertateMeta(block: ItemMeta.() -> Unit) = apply {
	itemMeta = itemMeta.apply(block)
}

fun ItemStack.modifyNBT(block: NBTModifier.NBTTagModifier.() -> Unit) = NBTModifier(this).modify(block)


class NBTModifier(itemStack: ItemStack) {
	private val nms = itemStack.convertToNNS()

	private val tag: NBTTagCompound

	init {
		tag = if (nms.hasTag()) nms.tag!! else NBTTagCompound()
	}

	fun modify(block: NBTTagModifier.() -> Unit) = apply {
		tag.set("AttributeModifiers", NBTTagList().apply {
			add(NBTTagModifier().apply(block).nbtTagCompound.apply {
				logger.warning(c().joinToString())
			})
		})
	}.getResult()

	private fun getResult() = nms.apply { tag = this@NBTModifier.tag }.convertToBukkit()

	class NBTTagModifier {
		val nbtTagCompound: NBTTagCompound = NBTTagCompound()

		interface INBTTagName {
			fun getNBTName(): String
		}

		enum class TagName : INBTTagName {
			Name, Type, Amount, Operation, UUIDLeast, UUIDMost, Slot;

			override fun getNBTName() = if (this == Type) "AttributeName" else name
		}

		enum class NBTType(private val nbtName: String) : INBTTagName {
			AttackDamage("attackDamage"),
			AttackSpeed("attackSpeed"),
			MaxHealth("maxHealth"),
			MovementSpeed("movementSpeed"),
			Armor("armor"),
			Luck("luck"),
			Undefined("null");

			override fun getNBTName() = "generic." + nbtName
		}

		enum class Slot(private val nbtName: String) : INBTTagName {
			MainHand("mainhand"),
			OffHand("offhand"),
			Feet("feet"),
			Legs("legs"),
			Chest("chest"),
			Head("head"),
			Undefined("null");

			override fun getNBTName() = nbtName
		}

		private fun setValue(name: TagName, value: NBTBase) {
			logger.info("添加$name:$value")
			nbtTagCompound.set(name.getNBTName(), value)
		}

		private fun remove(tagName: TagName) {
			if(Emerald.debug)
			logger.info("移除${tagName.name}")
			nbtTagCompound.remove(tagName.getNBTName())
		}

		private fun setTypeName(type: NBTType) {
			if(Emerald.debug)
			logger.info("添加Type:${type.getNBTName()}")
			nbtTagCompound.set("AttributeName", NBTTagString(type.getNBTName()))
		}


		companion object {
			const val DEFAULT_OPERATION = true
			const val DEFAULT_UUID_LEAST = 10000
			const val DEFAULT_UUID_MOST = 30000
		}

		var type: NBTType by NBTDelegate(NBTType.Undefined, TagName.Type)

		var slot: Slot by NBTDelegate(Slot.Undefined, TagName.Slot)

		var amount: Int by NBTDelegate(0, TagName.Amount)

		var operation: Boolean by NBTDelegate(DEFAULT_OPERATION, TagName.Operation)

		var uuidLeast: Int by NBTDelegate(DEFAULT_UUID_LEAST, TagName.UUIDLeast)

		var uuidMost: Int by NBTDelegate(DEFAULT_UUID_MOST, TagName.UUIDMost)


		private inner class NBTDelegate<T>(initialValue: T, private val tagName: TagName)
			: ReadWriteProperty<Any?, T> {
			var field = initialValue

			init {
				//强行设置初值
				setValue(null, this::field, field)
			}

			override fun getValue(thisRef: Any?, property: KProperty<*>) = field

			override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
				remove(tagName)
				when (value) {
					is String  -> setValue(tagName, NBTTagString(value))
					is Int     -> setValue(tagName, NBTTagInt(value))
					is Boolean -> setValue(tagName, NBTTagInt(if (value) 0 else 1))
					is NBTType -> {
						setTypeName(value)
						setValue(TagName.Name, NBTTagString(value.getNBTName() + RandomUtils.nextDouble()))
					}
					is Slot    -> setValue(tagName, NBTTagString(value.getNBTName()))

					else       -> throw IllegalStateException("喵喵喵?")
				}
				field = value
			}

		}
	}
}

