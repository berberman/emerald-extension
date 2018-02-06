[emerald-extension](../../../index.md) / [cn.berberman.emerald.dsl.command](../../index.md) / [DSLCommandBuilder](../index.md) / [TargetAndSenderBlocksData](.)

# TargetAndSenderBlocksData

`class TargetAndSenderBlocksData`

A class support function whenSenderIs, holds its data.

### Parameters

`senderInstance` - sender

`isTarget` - whether sender is target object

`result` - if it is, the result of execute

**Author**
berberman

**See Also**

[whenSenderIs](../when-sender-is.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TargetAndSenderBlocksData(senderInstance: CommandSender, isTarget: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, result: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`)`<br>A class support function whenSenderIs, holds its data. |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | `operator fun ~~invoke~~(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If don't use otherwise, you have to use invoke operator to get final result. We not recommended that. |
| [otherwise](otherwise.md) | `infix fun otherwise(block: CommandSender.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Set otherwise action and return final result. |
