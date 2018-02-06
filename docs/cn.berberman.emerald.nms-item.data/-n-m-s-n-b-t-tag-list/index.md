---
title: NMSNBTTagList - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.nmsItem.data](../index.html) / [NMSNBTTagList](.)

# NMSNBTTagList

`class NMSNBTTagList : `[`NMSReflection`](../../cn.berberman.emerald.nms-item/-n-m-s-reflection/index.html)

Corresponding NBTTagList
All methods are realized by reflection.

**Author**
berberman

### Constructors

| [&lt;init&gt;](-init-.html) | `NMSNBTTagList()`<br>Base constructor, which will new a NBTTagList instance.`NMSNBTTagList(original: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`)`<br>Advance constructor, use an initialed instance. |

### Properties

| [methods](methods.html) | `val methods: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Method>`<br>internal property to save realized methods You can't access this property, because it's inherited from NMSReflection. |
| [rawMethods](raw-methods.html) | `val rawMethods: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out Method>`<br>internal property to save all methods. You can't access this property, because it's inherited from NMSReflection. |
| [tagList](tag-list.html) | `val tagList: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)<br>an instance of NBTTagList holds by this class. |
| [targetNMSClass](target-n-m-s-class.html) | `val targetNMSClass: Class<*>`<br>internal property to save corresponding nms class.     You can't access this property, because it's inherited from NMSReflection. |

### Functions

| [add](add.html) | `fun add(any: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>remove a member to list. |
| [getMethod](get-method.html) | `fun getMethod(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Method`<br>internal function to get methods instance.     You can't access this method, because it's inherited from NMSReflection. |
| [remove](remove.html) | `fun remove(int: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>remove a member from list. |

