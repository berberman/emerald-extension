[emerald-extension](../index.md) / [cn.berberman.emerald.extension](index.md) / [async](.)

# async

`fun async(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): BukkitTask`

Start a async task.
Asynchronous tasks should never access any API in Bukkit. Great care
should be taken to assure the thread-safety of asynchronous tasks.

### Parameters

`delay` - the ticks to wait before running the task, default is 0

`block` - action to run

**Return**
a BukkitTask that contains the id number

