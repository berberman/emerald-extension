---
title: DSLCommandBuilder.TargetAndSenderBlocksData - emerald-extension
---

[emerald-extension](../../../index.html) / [cn.berberman.emerald.dsl.command](../../index.html) / [DSLCommandBuilder](../index.html) / [TargetAndSenderBlocksData](.)

# TargetAndSenderBlocksData

`class TargetAndSenderBlocksData`

A class support function whenSenderIs, holds its data.

### Parameters

`senderInstance` - sender

`isTarget` - whether sender is target type

`result` - if it is, the result of execute

**Author**
berberman

**See Also**

[whenSenderIs](../when-sender-is.html)

### Constructors

| [&lt;init&gt;](-init-.html) | `TargetAndSenderBlocksData(senderInstance: CommandSender, isTarget: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, result: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`<br>A class support function whenSenderIs, holds its data. |

### Functions

| [invoke](invoke.html) | `operator fun ~~invoke~~(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If you don't use otherwise, you have to use invoke operator to get final result. |
| [otherwise](otherwise.html) | `infix fun otherwise(block: CommandSender.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Set otherwise action and return final result. |

### Extension Functions

| [否则](../../../cn.berberman.emerald/否则.html) | `infix fun TargetAndSenderBlocksData.否则(block: CommandSender.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

