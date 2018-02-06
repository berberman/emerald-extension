---
title: PackingEvent - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.dsl.event](../index.html) / [PackingEvent](.)

# PackingEvent

`class PackingEvent<in T : Event>`

Packing a event with [org.bukkit.plugin.EventExecutor](#)

### Parameters

`type` - event type

`eventPriority` - eventPriority

`block` - action when event execute

**Author**
berberman

**See Also**

[org.bukkit.plugin.PluginManager.registerEvent](#)

### Constructors

| [&lt;init&gt;](-init-.html) | `PackingEvent(type: Class<out Event>, eventPriority: EventPriority, block: (T) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>Packing a event with [org.bukkit.plugin.EventExecutor](#) |

