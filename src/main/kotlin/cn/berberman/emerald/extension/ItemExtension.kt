package cn.berberman.emerald.extension

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.extension.NBTModifier.NBTTagModifier
import cn.berberman.emerald.nms.NMSUtil
import cn.berberman.emerald.nms.data.item.NMSItemStack
import cn.berberman.emerald.nms.data.nbt.NMSNBTTagCompound
import cn.berberman.emerald.nms.data.nbt.NMSNBTTagList
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

/**
 * NBT Modifier, let NBTTagModifier's value add to ItemStack.
 * @see NBTTagModifier
 * @author berberman
 * @param itemStack bukkit ItemStack
 */
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
	/**
	 * Modify NBT Tag.
	 * @see NBTModifier
	 * @author berberman
	 */
	class NBTTagModifier {
		internal val nbtTagCompound = NMSNBTTagCompound()

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
			const val DEFAULT_OPERATION = true
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
		 * `true` represents use amount
		 * `false` represents use %
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

					else       -> throw IllegalStateException("That's impossible.")
				}
				field = value
			}

		}
	}
}

