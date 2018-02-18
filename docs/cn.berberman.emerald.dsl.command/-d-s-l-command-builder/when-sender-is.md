---
title: DSLCommandBuilder.whenSenderIs - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.dsl.command](../index.html) / [DSLCommandBuilder](index.html) / [whenSenderIs](.)

# whenSenderIs

`inline infix fun <reified T : CommandSender> CommandSender.whenSenderIs(block: T.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`TargetAndSenderBlocksData`](-target-and-sender-blocks-data/index.html)

Provide a function to take place of ` if...else... `
    *infix fun can't declared generic explicitly*

### Parameters

`T` - type that assess whether sender is

`block` - action if sender is target type

**Receiver**
sender command sender

