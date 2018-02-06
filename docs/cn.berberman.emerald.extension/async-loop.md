[emerald-extension](../index.md) / [cn.berberman.emerald.extension](index.md) / [asyncLoop](.)

# asyncLoop

`fun asyncLoop(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): BukkitTask`

Start a async loop.
Asynchronous tasks should never access any API in Bukkit. Great care
should be taken to assure the thread-safety of asynchronous tasks.

### Parameters

`delay` - the ticks to wait before running the task, default is 0

`block` - action, keep running if it returns true

**Return**
a BukkitTask that contains the id number

