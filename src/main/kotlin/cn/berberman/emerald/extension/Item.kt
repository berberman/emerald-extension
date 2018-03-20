@file:JvmName("ItemStackUtil")


package cn.berberman.emerald.extension

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.NBTModifier.NBTTagBuilder
import cn.berberman.emerald.nms.wrapper.item.NmsItemStack
import cn.berberman.emerald.nms.wrapper.nbt.NmsNBTTagCompound
import cn.berberman.emerald.nms.wrapper.nbt.NmsNBTTagList
import cn.berberman.emerald.util.NmsUtil
import cn.berberman.emerald.util.ReflectionUtil
import org.apache.commons.lang.math.RandomUtils
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta
import org.bukkit.inventory.meta.BookMeta
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
 * @param block function with receiver to access DSL NBTTagBuilder
 * @return this ItemStack
 */
fun ItemStack.modifyNBT(block: NBTModifier.() -> Unit) = NBTModifier(this).apply(block).getResult()

//fun ItemStack.operateBookMeta(block: BukkitCraftMetaBook.() -> Unit) = apply {
//	itemMeta = BukkitCraftMetaBook(itemMeta as? BookMeta
//			?: throw IllegalStateException("This is not a book!")).apply(block).instance as ItemMeta?
//}
fun ItemStack.operateBookMeta(block: BookMeta.() -> Unit) = apply {
	itemMeta = itemMeta.safeCast<BookMeta>()?.apply(block)
			?: throw IllegalStateException("This is not a book!")
}

fun ItemStack.operateBannerMeta(block: BannerMeta.() -> Unit) = apply {
	itemMeta = itemMeta.safeCast<BannerMeta>()?.apply(block)
			?: throw IllegalStateException("This is not a banner!")
}

//@Deprecated("Unsafe")
//inline fun <reified T : ItemMeta> ItemStack.operateSpecificMeta(block: T.() -> Unit) =
//		apply { itemMeta = (itemMeta as? T)?.apply(block) ?: throw IllegalStateException("Meta cast error!") }

/**
 * NBT Modifier, let NBTTagBuilder's value add to ItemStack.
 * @see NBTTagBuilder
 * @author berberman
 * @param itemStack bukkit ItemStack
 */
class NBTModifier(itemStack: ItemStack) {
	private val nms = NmsItemStack(itemStack)

	private val tag: NmsNBTTagCompound

	init {
		tag = if (nms.hasTag()) nms.getTag() else NmsNBTTagCompound()
	}

	//dsl start
	/**
	 * Let outer class to access modifier.
	 */
	fun addTag(block: NBTTagBuilder.() -> Unit) = operateList {
		add(NBTTagBuilder().apply(block).nbtTagCompound.instance)
	}

	fun removeTagByIndex(index: Int) = operateList {
		remove(index)
	}

	fun removeTagByType(type: NBTTagBuilder.NBTType) = operateList {
		getInternal().map(::NmsNBTTagCompound).filter {
			ReflectionUtil.getField<String>(NmsUtil.getNMSClass("NBTTagString"),
					"data", it.get("AttributeName")!!) == type.getNBTName()
		}.let {
			getInternal().removeAll(it.map(NmsNBTTagCompound::instance))
		}
	}

	fun clearAllTags() = operateList { internalClear() }
	//dsl end

	private fun operateList(block: NmsNBTTagList.() -> Unit) =
			tag.set("AttributeModifiers", getOrNewList().apply(block).instance)

	private fun getOrNewList() = tag.get("AttributeModifiers")?.let { NmsNBTTagList(it) } ?: NmsNBTTagList()

	internal fun getResult() = NmsUtil.asBukkitCopy(nms.apply { setTag(this@NBTModifier.tag) })
	/**
	 * Modify NBT Tag.
	 * @see NBTModifier
	 * @author berberman
	 */
	class NBTTagBuilder {
		internal val nbtTagCompound = NmsNBTTagCompound()

		/**
		 * An enum which list data of NBT Name should implements this interface.
		 * @author berberman
		 */
		interface INBTTagName {
			/**
			 * Get NBT name
			 * @return returns NBT name
			 */
			fun getNBTName(): String
		}

		/**
		 * NBT Tag Name enumeration.
		 */
		internal enum class TagName : INBTTagName {
			Name,
			Type,
			Amount,
			Operation,
			UUIDLeast,
			UUIDMost,
			Slot;

			override fun getNBTName() = if (this == Type) "AttributeName" else name
		}

		/**
		 * NBT modify type enumeration.
		 */
		enum class NBTType(private val nbtName: String) : INBTTagName {
			/**
			 * modify attackDamage.
			 */
			AttackDamage("attackDamage"),
			/**
			 * modify attackSpeed.
			 */
			AttackSpeed("attackSpeed"),
			/**
			 *modify  maxHealth.
			 */
			MaxHealth("maxHealth"),
			/**
			 * modify movementSpeed.
			 */
			MovementSpeed("movementSpeed"),
			/**
			 * modify armor.
			 */
			Armor("armor"),
			/**
			 * modify luck.
			 */
			Luck("luck"),
			/**
			 * Should never be used.
			 */
			Undefined("null");

			override fun getNBTName() = "generic.$nbtName"
		}

		/**
		 * NBT slot enumeration.
		 */
		enum class Slot(private val nbtName: String) : INBTTagName {
			/**
			 * take effect when it's on the main hand.
			 */
			MainHand("mainhand"),
			/**
			 * take effect when it's on the off hand.
			 */
			OffHand("offhand"),
			/**
			 * take effect when it's wearing on the feet.
			 */
			Feet("feet"),
			/**
			 * take effect when it's wearing on the legs.
			 */
			Legs("legs"),
			/**
			 * take effect when it's wearing on the chest.
			 */
			Chest("chest"),
			/**
			 * take effect when it's wearing on the head.
			 */
			Head("head"),
			/**
			 * Should never be used.
			 */
			Undefined("null");

			override fun getNBTName() = nbtName
		}

		/**
		 * NBT operation enumeration
		 */
		enum class Operation(private val nbtValue: Short) {
			/**
			 * Operation 0: Additive. Adds all of the modifiers' amounts to the current value of the attribute.
			 * For example, modifying an attribute with {Amount:2,Operation:0} and {Amount:4,Operation:0}
			 * with a Base of 3 results in 9 (3 + 2 + 4 = 9).
			 */
			Additive(0),
			/**
			 * Operation 1: Multiplicative. Multiplies the current value of the attribute by (1 + x), where x is the sum of the modifiers' amounts.
			 * For example, modifying an attribute with {Amount:2,Operation:1} and {Amount:4,Operation:1}
			 * with a Base of 3 results in 21 (3 * (1 + 2 + 4) = 21).
			 */
			Multiplicative(1),
			/**
			 * Operation 2: Multiplicative. For every modifier, multiplies the current value of the attribute by (1 + x), where x is the amount of the particular modifier.
			 * Functions the same as Operation 1 if there is only a single modifier with operation 1 or 2. However, for multiple modifiers it will multiply the modifiers rather than adding them.
			 * For example, modifying an attribute with {Amount:2,Operation:2} and {Amount:4,Operation:2} with a Base of 3 results in 45 (3 * (1 + 2) * (1 + 4) = 45)
			 */
			@Deprecated("magic value", ReplaceWith("Multiplicative"))
			MultipleMultiplicative(2);

			fun getNBTValue() = nbtValue
		}

		private fun setString(tagName: TagName, value: String) {
			if (Emerald.debug)
				info("NBT:add $tagName:$value")
			nbtTagCompound.setString(tagName.getNBTName(), value)
		}

		private fun setInt(tagName: TagName, value: Int) {
			if (Emerald.debug)
				info("NBT:add $tagName:$value")
			nbtTagCompound.setInt(tagName.getNBTName(), value)
		}

		private fun setDouble(tagName: TagName, value: Double) {
			if (Emerald.debug)
				info("NBT:add $tagName:$value")
			nbtTagCompound.setDouble(tagName.getNBTName(), value)
		}

		private fun remove(tagName: TagName) {
			if (Emerald.debug)
				info("NBT:add ${tagName.name}")
			nbtTagCompound.remove(tagName.getNBTName())
		}

		private fun setTypeName(type: NBTType) {
			if (Emerald.debug)
				info("NBT:add Type:${type.getNBTName()}")
			nbtTagCompound.setString("AttributeName", type.getNBTName())
		}


		companion object {
			/**
			 * Default operation value
			 */
			val DEFAULT_OPERATION = Operation.Additive
			/**
			 * Default uuid least value
			 */
			const val DEFAULT_UUID_LEAST = 10000
			/**
			 * Default uuid most value
			 */
			const val DEFAULT_UUID_MOST = 30000
		}

		//dsl start
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
		var amount: Double by NBTDelegate(.0, TagName.Amount)
		/**
		 * NBT operation type, see [Operation]
		 */
		var operation: Operation by NBTDelegate(DEFAULT_OPERATION, TagName.Operation)
		/**
		 * uuidLeast value.
		 */
		var uuidLeast: Int by NBTDelegate(DEFAULT_UUID_LEAST, TagName.UUIDLeast)
		/**
		 * uuidMost value.
		 */
		var uuidMost: Int by NBTDelegate(DEFAULT_UUID_MOST, TagName.UUIDMost)
		//dsl end

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
					is String    -> setString(tagName, value)
					is Int       -> setInt(tagName, value)
					is Double    -> setDouble(tagName, value)
					is Operation -> setInt(tagName, value.getNBTValue().toInt())
					is NBTType   -> {
						setTypeName(value)
						setString(TagName.Name, (value.getNBTName() + RandomUtils.nextDouble()))
					}
					is Slot      -> setString(tagName, value.getNBTName())

					else         -> throw IllegalStateException("That's impossible.")
				}
				field = value
			}

		}
	}
}

