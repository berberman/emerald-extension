[emerald-extension](../../index.md) / [cn.berberman.emerald.dsl.event](../index.md) / [PackingEvent](.)

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

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PackingEvent(type: Class<out Event>, eventPriority: EventPriority, block: (T) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`)`<br>Packing a event with [org.bukkit.plugin.EventExecutor](#) |
