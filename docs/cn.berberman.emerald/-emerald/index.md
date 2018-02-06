---
title: Emerald - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald](../index.html) / [Emerald](.)

# Emerald

`object Emerald`

Main class of this extension.

**Author**
berberman

### Functions

| [registerCommands](register-commands.html) | `fun registerCommands(block: `[`DSLCommandScope`](../../cn.berberman.emerald.dsl.command/-d-s-l-command-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Register commands. |
| [registerContext](register-context.html) | `fun registerContext(plugin: JavaPlugin): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Quote plugin instance, must be call if you want to use this API. |
| [registerEvents](register-events.html) | `fun registerEvents(block: `[`DSLEventScope`](../../cn.berberman.emerald.dsl.event/-d-s-l-event-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Register events. |
| [registerPermissions](register-permissions.html) | `fun registerPermissions(block: `[`DSLPermissionScope`](../../cn.berberman.emerald.dsl.permission/-d-s-l-permission-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Register permissions. |
| [setDebug](set-debug.html) | `fun setDebug(boolean: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set whether print log of registration, command execute, NBT add. Default is false. |

