---
title: DSLCommandScope - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.dsl.command](../index.html) / [DSLCommandScope](.)

# DSLCommandScope

`class DSLCommandScope`

A DSL structure to build command builder.

**See Also**

[DSLCommandBuilder](../-d-s-l-command-builder/index.html)

[CommandHolder](#)

**Author**
berberman

### Properties

| [after](after.html) | `var after: (CommandSender, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Action after execute, default is empty.Read only. |
| [before](before.html) | `var before: (CommandSender, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Action before execute, default is empty.Read only. |

### Functions

| [after](after.html) | `fun after(block: (CommandSender, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set property after |
| [before](before.html) | `fun before(block: (CommandSender, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set property before |
| [command](command.html) | `fun command(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, block: `[`DSLCommandBuilder`](../-d-s-l-command-builder/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Build a command |

