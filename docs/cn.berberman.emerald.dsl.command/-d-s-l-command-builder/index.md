[emerald-extension](../../index.md) / [cn.berberman.emerald.dsl.command](../index.md) / [DSLCommandBuilder](.)

# DSLCommandBuilder

`class DSLCommandBuilder`

A DSL structure to build commands.

### Parameters

`name` - command name

**Author**
berberman

**See Also**

[PackingCommand](#)

### Types

| Name | Summary |
|---|---|
| [TargetAndSenderBlocksData](-target-and-sender-blocks-data/index.md) | `class TargetAndSenderBlocksData`<br>A class support function whenSenderIs, holds its data. |

### Properties

| Name | Summary |
|---|---|
| [action](action.md) | `var action: `[`Action`](../-action.md)<br>Read only, which will be invoked when commands execute. |
| [aliases](aliases.md) | `val aliases: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The aliases of command, default is empty. |
| [description](description.md) | `var description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The description of command, default is empty. |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [permission](permission.md) | `var permission: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The permission that this command needs, default is empty. |
| [permissionMessage](permission-message.md) | `var permissionMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The message will say if invoker doesn't have permission, default is empty. |
| [usageMessage](usage-message.md) | `var usageMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The usageMessage of command, default is empty. |

### Functions

| Name | Summary |
|---|---|
| [action](action.md) | `fun action(block: (CommandSender) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set action, only retain parameter command sender`fun action(block: (CommandSender, `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set action, retain parameters command sender and command arguments |
| [addAlias](add-alias.md) | `fun addAlias(alias: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Add alias to this command. |
| [whenSenderIs](when-sender-is.md) | `fun <T : CommandSender> whenSenderIs(sender: CommandSender, block: T.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`TargetAndSenderBlocksData`](-target-and-sender-blocks-data/index.md)<br>Provide a function to take place of  if...else...  |
