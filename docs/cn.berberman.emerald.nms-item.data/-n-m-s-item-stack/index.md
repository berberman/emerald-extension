[emerald-extension](../../index.md) / [cn.berberman.emerald.nmsItem.data](../index.md) / [NMSItemStack](.)

# NMSItemStack

`class NMSItemStack : `[`NMSReflection`](../../cn.berberman.emerald.nms-item/-n-m-s-reflection/index.md)

Corresponding ItemStack
All methods are realized by reflection.

### Parameters

`itemStack` - a bukkit itemStack instance

**Author**
berberman

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NMSItemStack(itemStack: ItemStack)`<br>Corresponding ItemStack All methods are realized by reflection. |

### Properties

| Name | Summary |
|---|---|
| [methods](methods.md) | `val methods: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Method>`<br>internal property to save realized methods. You can't access this property, because it's inherited from NMSReflection. |
| [nmsItemStack](nms-item-stack.md) | `val nmsItemStack: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>an instance of nmsItemStack holds by this class. |
| [rawMethods](raw-methods.md) | `val rawMethods: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out Method>`<br>internal property to save all methods. You can't access this property, because it's inherited from NMSReflection. |
| [targetNMSClass](target-n-m-s-class.md) | `val targetNMSClass: Class<*>`<br>internal property to save corresponding nms class.     You can't access this property, because it's inherited from NMSReflection. |

### Functions

| Name | Summary |
|---|---|
| [getMethod](get-method.md) | `fun getMethod(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Method`<br>internal function to get methods instance.     You can't access this method, because it's inherited from NMSReflection. |
| [getTag](get-tag.md) | `fun getTag(): `[`NMSNBTTagCompound`](../-n-m-s-n-b-t-tag-compound/index.md)<br>Get NBT Tag from nmsItem |
| [hasTag](has-tag.md) | `fun hasTag(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether nmsItem has NBT Tag. |
| [setTag](set-tag.md) | `fun setTag(nmsNBTTagCompound: `[`NMSNBTTagCompound`](../-n-m-s-n-b-t-tag-compound/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set nmsItem's NBT Tag |
