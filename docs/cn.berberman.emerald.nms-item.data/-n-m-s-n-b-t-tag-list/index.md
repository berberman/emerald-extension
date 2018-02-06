[emerald-extension](../../index.md) / [cn.berberman.emerald.nmsItem.data](../index.md) / [NMSNBTTagList](.)

# NMSNBTTagList

`class NMSNBTTagList : `[`NMSReflection`](../../cn.berberman.emerald.nms-item/-n-m-s-reflection/index.md)

Corresponding NBTTagList
All methods are realized by reflection.

**Author**
berberman

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NMSNBTTagList()`<br>Base constructor, which will new a NBTTagList instance.`NMSNBTTagList(original: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`)`<br>Advance constructor, use an initialed instance. |

### Properties

| Name | Summary |
|---|---|
| [methods](methods.md) | `val methods: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Method>`<br>internal property to save realized methods You can't access this property, because it's inherited from NMSReflection. |
| [rawMethods](raw-methods.md) | `val rawMethods: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out Method>`<br>internal property to save all methods. You can't access this property, because it's inherited from NMSReflection. |
| [tagList](tag-list.md) | `val tagList: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>an instance of NBTTagList holds by this class. |
| [targetNMSClass](target-n-m-s-class.md) | `val targetNMSClass: Class<*>`<br>internal property to save corresponding nms class.     You can't access this property, because it's inherited from NMSReflection. |

### Functions

| Name | Summary |
|---|---|
| [add](add.md) | `fun add(any: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>remove a member to list. |
| [getMethod](get-method.md) | `fun getMethod(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Method`<br>internal function to get methods instance.     You can't access this method, because it's inherited from NMSReflection. |
| [remove](remove.md) | `fun remove(int: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>remove a member from list. |
