package cn.berberman.emerald.extension.extension

import cn.berberman.emerald.extension.Emerald
import org.apache.commons.lang3.RandomUtils
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun ItemStack.opertateMeta(block: ItemMeta.() -> Unit) = apply {
	itemMeta = itemMeta.apply(block)
}

fun ItemStack.modifyNBT(block: NBTModifier.NBTTagModifier.() -> Unit) = NBTModifier(this).modify(block)

object NMSUtil {
	private val version = Bukkit.getServer()::class.java.`package`.name.split("\\.")[3]
	private val nmsPackageName = "net.minecraft.server.$version"
	private val craftBukkitPackageName = "org.bukkit.craftbukkit.$version"
	fun getNMSClass(name: String): Class<*> = Class.forName("$nmsPackageName.$name")

	fun getCraftBukkitClass(nameWithPackage: String): Class<*> = Class.forName("$craftBukkitPackageName.$nameWithPackage")

	fun asNMSCopy(original: ItemStack): Any = getCraftBukkitClass("inventory.CraftItemStack")
			.getMethod("asNMSCopy", ItemStack::class.java)(original)

	fun asBukkitCopy(original: NMSItemStack) = getCraftBukkitClass("inventory.CraftItemStack")
			.getMethod("asNMSCopy", ItemStack::class.java)(original.nmsItemStack) as ItemStack
}

class NMSNBTTagCompound {
	constructor() {
		tagCompound = NMSUtil.getNMSClass("NBTTagCompound").newInstance()
	}

	constructor(original: Any) {
		tagCompound = original
	}

	val tagCompound: Any

	private val rawMethods = NMSUtil.getNMSClass("NBTTagCompound").methods

	private fun getMethod(name: String) = rawMethods.firstOrNull { it.name == name }!!

	private val methods = hashMapOf(
			"setInt" to getMethod("setInt"),
			"setString" to getMethod("setString"),
			"remove" to getMethod("remove"),
			"set" to getMethod("set")
	)

	fun setInt(name: String, value: Int) {
		methods["setInt"]!!(tagCompound, name, value)
	}

	fun setString(name: String, value: String) {
		methods["setString"]!!(tagCompound, name, value)
	}

	fun remove(name: String) {
		methods["remove"]!!(tagCompound, name)
	}

	fun set(name: String, any: Any) {
		methods["set"]!!(tagCompound, name, any)
	}

}

class NMSNBTTagList {
	constructor() {
		tagList = NMSUtil.getNMSClass("NBTTagList").newInstance()
	}

	constructor(original: Any) {
		tagList = original
	}

	val tagList: Any

	private val rawMethods = NMSUtil.getNMSClass("NBTTagList").methods

	private fun getMethod(name: String) = rawMethods.firstOrNull { it.name == name }!!

	private val methods = hashMapOf(
			"remove" to getMethod("remove"),
			"add" to getMethod("add"),
			"c" to getMethod("c")
	)

	fun remove(int: Int) {
		methods["remove"]!!(tagList, int)
	}

	fun add(any: Any) {
		methods["add"]!!(tagList, any)
	}

	@Suppress("UNCHECKED_CAST")
	fun getContent() = methods["c"]!!(tagList) as Set<String>
}

class NMSItemStack(itemStack: ItemStack) {
	val nmsItemStack: Any = NMSUtil.asNMSCopy(itemStack)
	private val rawMethods = NMSUtil.getNMSClass("ItemStack").methods
	private fun getMethod(name: String) = rawMethods.firstOrNull { it.name == name }!!
	private val methods = hashMapOf(
			"hasTag" to getMethod("hasTag"),
			"getTag" to getMethod("getTag"),
			"setTag" to getMethod("setTag")
	)

	fun hasTag() = methods["hasTag"]!!(nmsItemStack) as Boolean

	fun getTag() = NMSNBTTagCompound(methods["getTag"]!!(nmsItemStack))

	fun setTag(nmsNbtTagCompound: NMSNBTTagCompound) {
		methods["setTag"]!!(nmsNbtTagCompound.tagCompound)
	}
}

class NBTModifier(itemStack: ItemStack) {
	private val nms = NMSItemStack(itemStack)

	private val tag: NMSNBTTagCompound

	init {
		tag = if (nms.hasTag()) nms.getTag() else NMSNBTTagCompound()
	}

	fun modify(block: NBTTagModifier.() -> Unit) = apply {
		tag.set("AttributeModifiers", NMSNBTTagList().apply {
			add(NBTTagModifier().apply(block).nbtTagCompound.apply {
				if (Emerald.debug)
					logger.warning("Tag内容" + getContent().joinToString())
			})
		}.tagList)
	}.getResult()

	private fun getResult() = NMSUtil.asBukkitCopy(nms.apply { setTag(this@NBTModifier.tag) })

	class NBTTagModifier {
		val nbtTagCompound = NMSNBTTagCompound()

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


		private fun setString(tagName: TagName, value: String) {
			if (Emerald.debug)
				logger.info("添加$tagName:$value")
			nbtTagCompound.setString(tagName.getNBTName(), value)
		}

		private fun setInt(tagName: TagName, int: Int) {
			if (Emerald.debug)
				logger.info("添加$tagName:$int")
			nbtTagCompound.setInt(tagName.getNBTName(), int)
		}

		private fun remove(tagName: TagName) {
			if (Emerald.debug)
				logger.info("移除${tagName.name}")
			nbtTagCompound.remove(tagName.getNBTName())
		}

		private fun setTypeName(type: NBTType) {
			if (Emerald.debug)
				logger.info("添加Type:${type.getNBTName()}")
			nbtTagCompound.setString("AttributeName", type.getNBTName())
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

