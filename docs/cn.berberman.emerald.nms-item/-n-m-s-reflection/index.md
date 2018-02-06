[emerald-extension](../../index.md) / [cn.berberman.emerald.nmsItem](../index.md) / [NMSReflection](.)

# NMSReflection

`abstract class NMSReflection`

An abstract class, describe what should a class that using reflection to corresponding nms class have.

**Author**
berberman

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NMSReflection()`<br>An abstract class, describe what should a class that using reflection to corresponding nms class have. |

### Properties

| Name | Summary |
|---|---|
| [methods](methods.md) | `abstract val methods: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Method>`<br>Methods we want's to use. |
| [rawMethods](raw-methods.md) | `abstract val rawMethods: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out Method>`<br>All methods of target class. |
| [targetNMSClass](target-n-m-s-class.md) | `abstract val targetNMSClass: Class<*>`<br>Which class we want's to corresponding. |

### Functions

| Name | Summary |
|---|---|
| [getMethod](get-method.md) | `abstract fun getMethod(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Method`<br>Find a method from rawMethods by name. |

### Inheritors

| Name | Summary |
|---|---|
| [NMSItemStack](../../cn.berberman.emerald.nms-item.data/-n-m-s-item-stack/index.md) | `class NMSItemStack : NMSReflection`<br>Corresponding ItemStack All methods are realized by reflection. |
| [NMSNBTTagCompound](../../cn.berberman.emerald.nms-item.data/-n-m-s-n-b-t-tag-compound/index.md) | `class NMSNBTTagCompound : NMSReflection`<br>Corresponding NBTTagCompound All methods are realized by reflection. |
| [NMSNBTTagList](../../cn.berberman.emerald.nms-item.data/-n-m-s-n-b-t-tag-list/index.md) | `class NMSNBTTagList : NMSReflection`<br>Corresponding NBTTagList All methods are realized by reflection. |
