---
title: runOnServerThread - emerald-extension
---

[emerald-extension](../index.html) / [cn.berberman.emerald.extension](index.html) / [runOnServerThread](.)

# runOnServerThread

`fun runOnServerThread(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): BukkitTask`

Start a task run on main thread.
Returns a task that will run on the next server tick.

### Parameters

`delay` - the ticks to wait before running the task, default is 0

`block` - action to run

**Return**
a BukkitTask that contains the id number

