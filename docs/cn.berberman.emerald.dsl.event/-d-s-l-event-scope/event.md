---
title: DSLEventScope.event - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.dsl.event](../index.html) / [DSLEventScope](index.html) / [event](.)

# event

`inline fun <reified T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL, noinline block: T.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Build a packing event.

### Parameters

`T` - target event

`eventPriority` - Event Priority, default is normal

`block` - DSL part of building event listeners