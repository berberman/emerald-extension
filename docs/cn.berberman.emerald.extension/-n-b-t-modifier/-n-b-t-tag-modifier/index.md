---
title: NBTModifier.NBTTagModifier - emerald-extension
---

[emerald-extension](../../../index.html) / [cn.berberman.emerald.extension](../../index.html) / [NBTModifier](../index.html) / [NBTTagModifier](.)

# NBTTagModifier

`class NBTTagModifier`

Modify NBT Tag.

**See Also**

[NBTModifier](../index.html)

**Author**
berberman

### Types

| [INBTTagName](-i-n-b-t-tag-name/index.html) | `interface INBTTagName`<br>An enum which list data of NBT Name should implements this interface. |
| [NBTType](-n-b-t-type/index.html) | `enum class NBTType : `[`INBTTagName`](-i-n-b-t-tag-name/index.html)<br>NBT modify type enumeration. |
| [Slot](-slot/index.html) | `enum class Slot : `[`INBTTagName`](-i-n-b-t-tag-name/index.html)<br>NBT slot enumeration. |

### Constructors

| [&lt;init&gt;](-init-.html) | `NBTTagModifier()`<br>Modify NBT Tag. |

### Properties

| [amount](amount.html) | `var amount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The value of NBT Tag |
| [operation](operation.html) | `var operation: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>true represents use amount false represents use % |
| [slot](slot.html) | `var slot: `[`Slot`](-slot/index.html)<br>Which slot where the item in this NBT Tag take effect. |
| [type](type.html) | `var type: `[`NBTType`](-n-b-t-type/index.html)<br>Which NBT Tag type you want to modify. |
| [uuidLeast](uuid-least.html) | `var uuidLeast: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>uuidLeast value. |
| [uuidMost](uuid-most.html) | `var uuidMost: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>uuidMost value. |

### Companion Object Properties

| [DEFAULT_OPERATION](-d-e-f-a-u-l-t_-o-p-e-r-a-t-i-o-n.html) | `const val DEFAULT_OPERATION: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Default operation value |
| [DEFAULT_UUID_LEAST](-d-e-f-a-u-l-t_-u-u-i-d_-l-e-a-s-t.html) | `const val DEFAULT_UUID_LEAST: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default uuid least value |
| [DEFAULT_UUID_MOST](-d-e-f-a-u-l-t_-u-u-i-d_-m-o-s-t.html) | `const val DEFAULT_UUID_MOST: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Default uuid most value |

