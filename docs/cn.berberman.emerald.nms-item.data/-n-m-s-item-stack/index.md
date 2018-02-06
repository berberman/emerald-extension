---
title: NMSItemStack - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.nmsItem.data](../index.html) / [NMSItemStack](.)

# NMSItemStack

`class NMSItemStack : `[`NMSReflection`](../../cn.berberman.emerald.nms-item/-n-m-s-reflection/index.html)

Corresponding ItemStack
All methods are realized by reflection.

### Parameters

`itemStack` - a bukkit itemStack instance

**Author**
berberman

### Constructors

| [&lt;init&gt;](-init-.html) | `NMSItemStack(itemStack: ItemStack)`<br>Corresponding ItemStack All methods are realized by reflection. |

### Properties

| [methods](methods.html) | `val methods: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Method>`<br>internal property to save realized methods. You can't access this property, because it's inherited from NMSReflection. |
| [nmsItemStack](nms-item-stack.html) | `val nmsItemStack: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>an instance of nmsItemStack holds by this class. |
| [rawMethods](raw-methods.html) | `val rawMethods: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out Method>`<br>internal property to save all methods. You can't access this property, because it's inherited from NMSReflection. |
| [targetNMSClass](target-n-m-s-class.html) | `val targetNMSClass: Class<*>`<br>internal property to save corresponding nms class.     You can't access this property, because it's inherited from NMSReflection. |

### Functions

| [getMethod](get-method.html) | `fun getMethod(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Method`<br>internal function to get methods instance.     You can't access this method, because it's inherited from NMSReflection. |
| [getTag](get-tag.html) | `fun getTag(): `[`NMSNBTTagCompound`](../-n-m-s-n-b-t-tag-compound/index.html)<br>Get NBT Tag from nmsItem |
| [hasTag](has-tag.html) | `fun hasTag(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether nmsItem has NBT Tag. |
| [setTag](set-tag.html) | `fun setTag(nmsNBTTagCompound: `[`NMSNBTTagCompound`](../-n-m-s-n-b-t-tag-compound/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set nmsItem's NBT Tag |

