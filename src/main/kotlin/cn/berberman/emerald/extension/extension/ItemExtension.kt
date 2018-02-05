package cn.berberman.emerald.extension.extension

import cn.berberman.emerald.extension.Emerald
import cn.berberman.emerald.extension.dsl.nms.item.NMSUtil
import cn.berberman.emerald.extension.dsl.nms.item.data.NMSItemStack
import cn.berberman.emerald.extension.dsl.nms.item.data.NMSNBTTagCompound
import cn.berberman.emerald.extension.dsl.nms.item.data.NMSNBTTagList
import org.apache.commons.lang.math.RandomUtils
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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
 * @param block function with receiver to access DSL NBTTagModifier
 * @return this ItemStack
 */
fun ItemStack.modifyNBT(block: NBTModifier.NBTTagModifier.() -> Unit) = NBTModifier(this).modify(block)


class NBTModifier(itemStack: ItemStack) {
	private val nms = NMSItemStack(itemStack)

	private val tag: NMSNBTTagCompound

	init {
		tag = if (nms.hasTag()) nms.getTag() else NMSNBTTagCompound()
	}

	/**
	 * Let outer class to access modifier.
	 */
	internal fun modify(block: NBTTagModifier.() -> Unit) = apply {
		tag.set("AttributeModifiers", NMSNBTTagList().apply {
			add(NBTTagModifier().apply(block).nbtTagCompound.tagCompound)
		}.tagList)
	}.getResult()

	private fun getResult() = NMSUtil.asBukkitCopy(nms.apply { setTag(this@NBTModifier.tag) })

	class NBTTagModifier {
		internal val nbtTagCompound = NMSNBTTagCompound()

		/**
		 * An enum which list data of NBT Name should implements this interface.
		 * @author berberman
		 */
		interface INBTTagName {
			fun getNBTName(): String
		}

		/**
		 * NBT Tag Name enumeration.
		 */
		enum class TagName : INBTTagName {
			Name, Type, Amount, Operation, UUIDLeast, UUIDMost, Slot;

			override fun getNBTName() = if (this == Type) "AttributeName" else name
		}

		/**
		 * NBT modify type enumeration.
		 */
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

		/**
		 * NBT slot enumeration.
		 */
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


		private fun setString(tagName: TagName, value: String) {
			if (Emerald.debug)
				logger.info("NBT:添加$tagName:$value")
			nbtTagCompound.setString(tagName.getNBTName(), value)
		}

		private fun setInt(tagName: TagName, int: Int) {
			if (Emerald.debug)
				logger.info("NBT:添加$tagName:$int")
			nbtTagCompound.setInt(tagName.getNBTName(), int)
		}

		private fun remove(tagName: TagName) {
			if (Emerald.debug)
				logger.info("NBT:移除${tagName.name}")
			nbtTagCompound.remove(tagName.getNBTName())
		}

		private fun setTypeName(type: NBTType) {
			if (Emerald.debug)
				logger.info("NBT:添加Type:${type.getNBTName()}")
			nbtTagCompound.setString("AttributeName", type.getNBTName())
		}


		companion object {
			const val DEFAULT_OPERATION = true
			const val DEFAULT_UUID_LEAST = 10000
			const val DEFAULT_UUID_MOST = 30000
		}

		/**
		 * Which NBT Tag type you want to modify.
		 */
		var type: NBTType by NBTDelegate(NBTType.Undefined, TagName.Type)
		/**
		 * Which slot where the item in this NBT Tag take effect.
		 */
		var slot: Slot by NBTDelegate(Slot.Undefined, TagName.Slot)
		/**
		 * The value of NBT Tag
		 */
		var amount: Int by NBTDelegate(0, TagName.Amount)
		/**
		 * <tt>true</tt> represents use amount
		 * <tt>false</tt> represents use %
		 */
		var operation: Boolean by NBTDelegate(DEFAULT_OPERATION, TagName.Operation)
		/**
		 * uuidLeast value.
		 */
		var uuidLeast: Int by NBTDelegate(DEFAULT_UUID_LEAST, TagName.UUIDLeast)
		/**
		 * uuidMost value.
		 */
		var uuidMost: Int by NBTDelegate(DEFAULT_UUID_MOST, TagName.UUIDMost)


		private inner class NBTDelegate<T>(initialValue: T, private val tagName: TagName)
			: ReadWriteProperty<Any?, T> {
			internal var field = initialValue

			init {
				//force initialize
				setValue(null, this::field, field)
			}

			override fun getValue(thisRef: Any?, property: KProperty<*>) = field

			override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
				remove(tagName)
				when (value) {
					is String  -> setString(tagName, value)
					is Int     -> setInt(tagName, value)
					is Boolean -> setInt(tagName, if (value) 0 else 1)
					is NBTType -> {
						setTypeName(value)
						setString(TagName.Name, (value.getNBTName() + RandomUtils.nextDouble()))
					}
					is Slot    -> setString(tagName, value.getNBTName())

					else       -> throw IllegalStateException("喵喵喵?")
				}
				field = value
			}

		}
	}
}

