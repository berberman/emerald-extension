---
title: NMSReflection - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.nmsItem](../index.html) / [NMSReflection](.)

# NMSReflection

`abstract class NMSReflection`

An abstract class, describe what should a class that using reflection to corresponding nms class have.

**Author**
berberman

### Constructors

| [&lt;init&gt;](-init-.html) | `NMSReflection()`<br>An abstract class, describe what should a class that using reflection to corresponding nms class have. |

### Properties

| [methods](methods.html) | `abstract val methods: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Method>`<br>Methods we want's to use. |
| [rawMethods](raw-methods.html) | `abstract val rawMethods: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out Method>`<br>All methods of target class. |
| [targetNMSClass](target-n-m-s-class.html) | `abstract val targetNMSClass: Class<*>`<br>Which class we want's to corresponding. |

### Functions

| [getMethod](get-method.html) | `abstract fun getMethod(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Method`<br>Find a method from rawMethods by name. |

### Inheritors

| [NMSItemStack](../../cn.berberman.emerald.nms-item.data/-n-m-s-item-stack/index.html) | `class NMSItemStack : NMSReflection`<br>Corresponding ItemStack All methods are realized by reflection. |
| [NMSNBTTagCompound](../../cn.berberman.emerald.nms-item.data/-n-m-s-n-b-t-tag-compound/index.html) | `class NMSNBTTagCompound : NMSReflection`<br>Corresponding NBTTagCompound All methods are realized by reflection. |
| [NMSNBTTagList](../../cn.berberman.emerald.nms-item.data/-n-m-s-n-b-t-tag-list/index.html) | `class NMSNBTTagList : NMSReflection`<br>Corresponding NBTTagList All methods are realized by reflection. |

