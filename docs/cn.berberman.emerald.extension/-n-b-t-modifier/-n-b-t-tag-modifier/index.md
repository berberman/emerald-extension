[emerald-extension](../../../index.md) / [cn.berberman.emerald.extension](../../index.md) / [NBTModifier](../index.md) / [NBTTagModifier](.)

# NBTTagModifier

`class NBTTagModifier`

Modify NBT Tag.

**See Also**

[NBTModifier](../index.md)

**Author**
berberman

### Types

| Name | Summary |
|---|---|
| [INBTTagName](-i-n-b-t-tag-name/index.md) | `interface INBTTagName`<br>An enum which list data of NBT Name should implements this interface. |
| [NBTType](-n-b-t-type/index.md) | `enum class NBTType : `[`INBTTagName`](-i-n-b-t-tag-name/index.md)<br>NBT modify type enumeration. |
| [Slot](-slot/index.md) | `enum class Slot : `[`INBTTagName`](-i-n-b-t-tag-name/index.md)<br>NBT slot enumeration. |
| [TagName](-tag-name/index.md) | `enum class TagName : `[`INBTTagName`](-i-n-b-t-tag-name/index.md)<br>NBT Tag Name enumeration. |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `NBTTagModifier()`<br>Modify NBT Tag. |

### Properties

| Name | Summary |
|---|---|
| [amount](amount.md) | `var amount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The value of NBT Tag |
| [operation](operation.md) | `var operation: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>true represents use amount false represents use % |
| [slot](slot.md) | `var slot: `[`Slot`](-slot/index.md)<br>Which slot where the item in this NBT Tag take effect. |
| [type](type.md) | `var type: `[`NBTType`](-n-b-t-type/index.md)<br>Which NBT Tag type you want to modify. |
| [uuidLeast](uuid-least.md) | `var uuidLeast: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>uuidLeast value. |
| [uuidMost](uuid-most.md) | `var uuidMost: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>uuidMost value. |

### Companion Object Properties

| Name | Summary |
|---|---|
| [DEFAULT_OPERATION](-d-e-f-a-u-l-t_-o-p-e-r-a-t-i-o-n.md) | `const val DEFAULT_OPERATION: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [DEFAULT_UUID_LEAST](-d-e-f-a-u-l-t_-u-u-i-d_-l-e-a-s-t.md) | `const val DEFAULT_UUID_LEAST: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [DEFAULT_UUID_MOST](-d-e-f-a-u-l-t_-u-u-i-d_-m-o-s-t.md) | `const val DEFAULT_UUID_MOST: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
