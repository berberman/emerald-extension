package cn.berberman.emerald.dsl.item.tag

import cn.berberman.emerald.extension.debug
import org.apache.commons.lang.math.RandomUtils
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Modify NBT Attribute Tag.
 * @author berberman
 */
class NBTAttributeTagBuilder : NBTTagBuilder() {
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

	private fun setTypeName(type: NBTType) {
		debug("NBT:add Type:${type.getNBTName()}")
		nbtTagCompound.setString("AttributeName", type.getNBTName())
	}

	private fun String.toNBTTagName(transformer: (String) -> String) =
			transformer(this).let {
				object : INBTTagName {
					override fun getNBTName(): String = it
				}
			}


	private fun setString(tagName: INBTTagName, value: String) {
		setString(tagName.getNBTName(), value)
	}

	private fun setInt(tagName: INBTTagName, value: Int) {
		setInt(tagName.getNBTName(), value)
	}

	private fun setDouble(tagName: INBTTagName, value: Double) {
		setDouble(tagName.getNBTName(), value)
	}

	private fun remove(tagName: INBTTagName) {
		remove(tagName.getNBTName())
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
	var type: NBTType by NBTAttributeTagDelegate(NBTType.Undefined, TagName.Type)
	/**
	 * Which slot where the item in this NBT Tag take effect.
	 */
	var slot: Slot by NBTAttributeTagDelegate(Slot.Undefined, TagName.Slot)
	/**
	 * The value of NBT Tag
	 */
	var amount: Double by NBTAttributeTagDelegate(.0, TagName.Amount)
	/**
	 * NBT operation type, see [Operation]
	 */
	var operation: Operation by NBTAttributeTagDelegate(DEFAULT_OPERATION, TagName.Operation)
	/**
	 * uuidLeast value.
	 */
	var uuidLeast: Int by NBTAttributeTagDelegate(DEFAULT_UUID_LEAST, TagName.UUIDLeast)
	/**
	 * uuidMost value.
	 */
	var uuidMost: Int by NBTAttributeTagDelegate(DEFAULT_UUID_MOST, TagName.UUIDMost)

	/**
	 * tag name
	 */
	var name: String by NBTAttributeTagDelegate("random", TagName.Name)
	//dsl end

	private inner class NBTAttributeTagDelegate<T>(initialValue: T, private val tagName: TagName)
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
				is String    -> setString(tagName, if (value == "random")
					RandomUtils.nextDouble().toString() else value)
				is Int       -> setInt(tagName, value)
				is Double    -> setDouble(tagName, value)
				is Operation -> setInt(tagName, value.getNBTValue().toInt())
				is NBTType   -> {
					setTypeName(value)
//						setString(TagName.Name, (value.getNBTName() + RandomUtils.nextDouble()))
				}
				is Slot      -> setString(tagName, value.getNBTName())
				else         -> throw IllegalStateException("That's impossible.")
			}
			field = value
		}

	}
}