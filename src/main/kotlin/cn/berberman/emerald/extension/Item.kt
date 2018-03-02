package cn.berberman.emerald.extension

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.NBTModifier.NBTTagModifier
import cn.berberman.emerald.nms.data.item.NmsItemStack
import cn.berberman.emerald.nms.data.meta.BukkitCraftMetaBook
import cn.berberman.emerald.nms.data.nbt.NmsNBTTagCompound
import cn.berberman.emerald.nms.data.nbt.NmsNBTTagList
import cn.berberman.emerald.util.NmsUtil
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
 * @param block function with receiver to access DSL NBTTagModifier
 * @return this ItemStack
 */
fun ItemStack.addNBTTag(block: NBTModifier.NBTTagModifier.() -> Unit) = NBTModifier(this).modify(block)

fun ItemStack.operateBookMeta(block: BukkitCraftMetaBook.() -> Unit) = apply {
	itemMeta = BukkitCraftMetaBook(itemMeta as? BookMeta
			?: throw IllegalStateException("This is not a book!")).apply(block).instance as ItemMeta?
}

fun ItemStack.operateBannerMeta(block: BannerMeta.() -> Unit) = apply {
	itemMeta = (itemMeta as? BannerMeta)?.apply(block) ?: throw IllegalStateException("This is not a banner!")
}

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Unsafe")
inline fun <reified T : ItemMeta> ItemStack.operateSpecificMeta(block: T.() -> Unit) =
		apply { itemMeta = (itemMeta as? T)?.apply(block) ?: throw IllegalStateException("Meta cast error!") }

/**
 * NBT Modifier, let NBTTagModifier's value add to ItemStack.
 * @see NBTTagModifier
 * @author berberman
 * @param itemStack bukkit ItemStack
 */
class NBTModifier(itemStack: ItemStack) {
	private val nms = NmsItemStack(itemStack)

	private val tag: NmsNBTTagCompound

	init {
		tag = if (nms.hasTag()) nms.getTag() else NmsNBTTagCompound()
	}

	/**
	 * Let outer class to access modifier.
	 */
	internal fun modify(block: NBTTagModifier.() -> Unit) = apply {
		tag.set("AttributeModifiers", NmsNBTTagList().apply {
			add(NBTTagModifier().apply(block).nbtTagCompound.instance)
		}.instance)
	}.getResult()

	private fun getResult() = NmsUtil.asBukkitCopy(nms.apply { setTag(this@NBTModifier.tag) })
	/**
	 * Modify NBT Tag.
	 * @see NBTModifier
	 * @author berberman
	 */
	class NBTTagModifier {
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

			override fun getNBTName() = "generic." + nbtName
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
			MultipleMultiplicative(2);

			fun getNBTValue() = nbtValue
		}

		private fun setString(tagName: TagName, value: String) {
			if (Emerald.debug)
				info("NBT:add $tagName:$value")
			nbtTagCompound.setString(tagName.getNBTName(), value)
		}

		private fun setInt(tagName: TagName, int: Int) {
			if (Emerald.debug)
				info("NBT:add $tagName:$int")
			nbtTagCompound.setInt(tagName.getNBTName(), int)
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

