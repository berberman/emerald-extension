[emerald-extension](../../index.md) / [cn.berberman.emerald.dsl.command](../index.md) / [DSLCommandBuilder](index.md) / [whenSenderIs](.)

# whenSenderIs

`inline fun <reified T : CommandSender> whenSenderIs(sender: CommandSender, block: T.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`TargetAndSenderBlocksData`](-target-and-sender-blocks-data/index.md)

Provide a function to take place of  if...else...

### Parameters

`T` - target type that assess whether sender is

`sender` - command sender

`block` - action if sender is target object