[emerald-extension](../../index.md) / [cn.berberman.emerald.dsl.command](../index.md) / [DSLCommandScope](.)

# DSLCommandScope

`class DSLCommandScope`

A DSL structure to build command builder.

**See Also**

[DSLCommandBuilder](../-d-s-l-command-builder/index.md)

[CommandHolder](#)

**Author**
berberman

### Properties

| Name | Summary |
|---|---|
| [after](after.md) | `var after: (CommandSender, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Action after execute, default is empty.Read only. |
| [before](before.md) | `var before: (CommandSender, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Action before execute, default is empty.Read only. |

### Functions

| Name | Summary |
|---|---|
| [after](after.md) | `fun after(block: (CommandSender, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set property after |
| [before](before.md) | `fun before(block: (CommandSender, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Set property before |
| [command](command.md) | `fun command(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, block: `[`DSLCommandBuilder`](../-d-s-l-command-builder/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Build a command |
