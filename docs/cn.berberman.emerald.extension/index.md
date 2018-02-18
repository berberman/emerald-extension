---
title: cn.berberman.emerald.extension - emerald-extension
---

[emerald-extension](../index.html) / [cn.berberman.emerald.extension](.)

## Package cn.berberman.emerald.extension

### Types

| [Axis3D](-axis3-d/index.html) | `enum class Axis3D`<br>Represents the axis of three dimension. |
| [NBTModifier](-n-b-t-modifier/index.html) | `class NBTModifier`<br>NBT Modifier, let NBTTagModifier's value add to ItemStack. |

### Extensions for External Classes

| [java.lang.Class](java.lang.-class/index.html) |  |
| [org.bukkit.ChatColor](org.bukkit.-chat-color/index.html) |  |
| [org.bukkit.Location](org.bukkit.-location/index.html) |  |
| [org.bukkit.World](org.bukkit.-world/index.html) |  |
| [org.bukkit.command.CommandSender](org.bukkit.command.-command-sender/index.html) |  |
| [org.bukkit.entity.Player](org.bukkit.entity.-player/index.html) |  |
| [org.bukkit.inventory.ItemStack](org.bukkit.inventory.-item-stack/index.html) |  |
| [org.bukkit.util.Vector](org.bukkit.util.-vector/index.html) |  |

### Properties

| [logger](logger.html) | `val logger: Logger`<br>Plugin's logger. |
| [plugin](plugin.html) | `val plugin: JavaPlugin`<br>Quoted plugin instance. |
| [pluginManager](plugin-manager.html) | `val pluginManager: PluginManager`<br>Plugin manager. |

### Functions

| [createInventory](create-inventory.html) | `fun createInventory(holder: InventoryHolder? = null, lines: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, type: InventoryType? = null, title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): Inventory`<br>Creates an empty inventory. |
| [emeraldAsync](emerald-async.html) | `fun ~~emeraldAsync~~(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): BukkitTask`<br>Start an async task. *Asynchronous tasks should never access any API in Bukkit. Great careshould be taken to assure the thread-safety of asynchronous tasks.* |
| [emeraldAsyncLoop](emerald-async-loop.html) | `fun ~~emeraldAsyncLoop~~(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): BukkitTask`<br>Start an async loop. *Asynchronous tasks should never access any API in Bukkit. Great careshould be taken to assure the thread-safety of asynchronous tasks.* |
| [getPluginBean](get-plugin-bean.html) | `fun <T : JavaPlugin> getPluginBean(): T`<br>Get one plugin's instance. |
| [info](info.html) | `fun info(msg: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun info(supplier: () -> `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Log a INFO message, which is only to be constructed if the logging level is such that the message will actually be logged. |
| [runOnServerThread](run-on-server-thread.html) | `fun runOnServerThread(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): BukkitTask`<br>Start a task run on main thread. Returns a task that will run on the next server tick. |
| [warning](warning.html) | `fun warning(msg: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Log a WARNING message.`fun warning(supplier: () -> `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Log a WARNING message, which is only to be constructed if the logging level is such that the message will actually be logged. |

