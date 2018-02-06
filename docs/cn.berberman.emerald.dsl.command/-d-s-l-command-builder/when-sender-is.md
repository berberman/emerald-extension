---
title: DSLCommandBuilder.whenSenderIs - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.dsl.command](../index.html) / [DSLCommandBuilder](index.html) / [whenSenderIs](.)

# whenSenderIs

`inline fun <reified T : CommandSender> whenSenderIs(sender: CommandSender, block: T.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`TargetAndSenderBlocksData`](-target-and-sender-blocks-data/index.html)

Provide a function to take place of ` if...else... `

### Parameters

`T` - target type that assess whether sender is

`sender` - command sender

`block` - action if sender is target object