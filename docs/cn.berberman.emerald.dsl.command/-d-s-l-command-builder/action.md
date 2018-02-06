[emerald-extension](../../index.md) / [cn.berberman.emerald.dsl.command](../index.md) / [DSLCommandBuilder](index.md) / [action](.)

# action

`var action: `[`Action`](../-action.md)

Read only, which will be invoked when commands execute.

`fun action(block: (CommandSender) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set action, only retain parameter command sender

### Parameters

`block` - a function contains the action of execute command,
return boolean represents whether command executes successfully`fun action(block: (CommandSender, `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set action, retain parameters command sender and command arguments

### Parameters

`block` - a function contains the action of execute command,
return boolean represents whether command executes successfully