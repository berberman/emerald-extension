[emerald-extension](../../index.md) / [cn.berberman.emerald.nmsItem.data](../index.md) / [NMSNBTTagCompound](.)

# NMSNBTTagCompound

`class NMSNBTTagCompound : `[`NMSReflection`](../../cn.berberman.emerald.nms-item/-n-m-s-reflection/index.md)

Corresponding NBTTagCompound
All methods are realized by reflection.

**Author**
berberman

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NMSNBTTagCompound()`<br>Base constructor, which will new a NBTTagCompound instance.`NMSNBTTagCompound(original: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`)`<br>Advance constructor, use an initialed instance. |

### Properties

| Name | Summary |
|---|---|
| [methods](methods.md) | `val methods: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Method>`<br>internal property to save realized methods You can't access this property, because it's inherited from NMSReflection. |
| [rawMethods](raw-methods.md) | `val rawMethods: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out Method>`<br>internal property to save all methods. You can't access this property, because it's inherited from NMSReflection. |
| [tagCompound](tag-compound.md) | `val tagCompound: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>an instance of NBTTagCompound holds by this class. |
| [targetNMSClass](target-n-m-s-class.md) | `val targetNMSClass: Class<*>`<br>internal property to save corresponding nms class.     You can't access this property, because it's inherited from NMSReflection. |

### Functions

| Name | Summary |
|---|---|
| [getMethod](get-method.md) | `fun getMethod(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Method`<br>internal function to get methods instance.     You can't access this method, because it's inherited from NMSReflection. |
| [remove](remove.md) | `fun remove(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>remove a value from this compound. |
| [set](set.md) | `fun set(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, any: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set raw value |
| [setInt](set-int.md) | `fun setInt(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set int value |
| [setString](set-string.md) | `fun setString(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>set String value |
