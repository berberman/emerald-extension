---
title: DSLCommandBuilder - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.dsl.command](../index.html) / [DSLCommandBuilder](.)

# DSLCommandBuilder

`class DSLCommandBuilder`

A DSL structure to build commands.

### Parameters

`name` - command name

**Author**
berberman

### Types

| [TargetAndSenderBlocksData](-target-and-sender-blocks-data/index.html) | `class TargetAndSenderBlocksData`<br>A class support function whenSenderIs, holds its data. |

### Properties

| [action](action.html) | `var action: `[`Action`](../-action.html)<br>Read only, which will be invoked when commands execute. |
| [aliases](aliases.html) | `val aliases: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>The aliases of command, default is empty. |
| [description](description.html) | `var description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The description of command, default is empty. |
| [permission](permission.html) | `var permission: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The permission that this command needs, default is empty. |
| [permissionMessage](permission-message.html) | `var permissionMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The message will say if invoker doesn't have permission, default is empty. |
| [usageMessage](usage-message.html) | `var usageMessage: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The usageMessage of command, default is empty. |

### Functions

| [action](action.html) | `fun action(block: (CommandSender) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set action, only retain parameter command sender`fun action(block: (CommandSender, `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set action, retain parameters command sender and command arguments |
| [addAlias](add-alias.html) | `fun addAlias(alias: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Add alias to this command. |
| [whenSenderIs](when-sender-is.html) | `fun <T : CommandSender> whenSenderIs(sender: CommandSender, block: T.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`TargetAndSenderBlocksData`](-target-and-sender-blocks-data/index.html)<br>Provide a function to take place of ` if...else... ` |

