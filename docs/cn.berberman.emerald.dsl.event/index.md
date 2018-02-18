---
title: cn.berberman.emerald.dsl.event - emerald-extension
---

[emerald-extension](../index.html) / [cn.berberman.emerald.dsl.event](.)

## Package cn.berberman.emerald.dsl.event

### Types

| [DSLEventScope](-d-s-l-event-scope/index.html) | `class DSLEventScope`<br>A DSL structure to build events. |
| [PackingEvent](-packing-event/index.html) | `class PackingEvent<in T : Event>`<br>Packing a event with org.bukkit.plugin.EventExecutor |

### Functions

| [event](event.html) | `fun <T : Event> event(eventPriority: EventPriority = EventPriority.NORMAL, block: T.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`PackingEvent`](-packing-event/index.html)`<T>`<br>Build a packing event. |
| [registerEvent](register-event.html) | `fun <T : Event> registerEvent(packingEvent: `[`PackingEvent`](-packing-event/index.html)`<T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T : Event> registerEvent(supplier: () -> `[`PackingEvent`](-packing-event/index.html)`<T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Register event. |
| [registerEvents](register-events.html) | `fun registerEvents(block: `[`DSLEventScope`](-d-s-l-event-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`DSLEventScope`](-d-s-l-event-scope/index.html)<br>Register events. |
| [unregisterEvent](unregister-event.html) | `fun <T : Event> unregisterEvent(packingEvent: `[`PackingEvent`](-packing-event/index.html)`<T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T : Event> unregisterEvent(supplier: () -> `[`PackingEvent`](-packing-event/index.html)`<T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Unregister event |

