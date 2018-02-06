[emerald-extension](../index.md) / [cn.berberman.emerald.extension](.)

## Package cn.berberman.emerald.extension

### Types

| Name | Summary |
|---|---|
| [Axis3D](-axis3-d/index.md) | `enum class Axis3D`<br>Represents the axis of three dimension. |
| [NBTModifier](-n-b-t-modifier/index.md) | `class NBTModifier`<br>NBT Modifier, let NBTTagModifier's value add to ItemStack. |

### Extensions for External Classes

| Name | Summary |
|---|---|
| [org.bukkit.ChatColor](org.bukkit.-chat-color/index.md) |  |
| [org.bukkit.Location](org.bukkit.-location/index.md) |  |
| [org.bukkit.World](org.bukkit.-world/index.md) |  |
| [org.bukkit.command.CommandSender](org.bukkit.command.-command-sender/index.md) |  |
| [org.bukkit.inventory.ItemStack](org.bukkit.inventory.-item-stack/index.md) |  |
| [org.bukkit.util.Vector](org.bukkit.util.-vector/index.md) |  |

### Properties

| Name | Summary |
|---|---|
| [logger](logger.md) | `val logger: Logger`<br>Plugin's logger. |
| [plugin](plugin.md) | `val plugin: JavaPlugin`<br>Quoted plugin instance. |
| [pluginManager](plugin-manager.md) | `val pluginManager: PluginManager`<br>Plugin manager. |

### Functions

| Name | Summary |
|---|---|
| [async](async.md) | `fun async(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): BukkitTask`<br>Start a async task. Asynchronous tasks should never access any API in Bukkit. Great care should be taken to assure the thread-safety of asynchronous tasks. |
| [asyncLoop](async-loop.md) | `fun asyncLoop(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): BukkitTask`<br>Start a async loop. Asynchronous tasks should never access any API in Bukkit. Great care should be taken to assure the thread-safety of asynchronous tasks. |
| [getPluginBean](get-plugin-bean.md) | `fun <T : JavaPlugin> getPluginBean(): T`<br>Get one plugin's instance. |
| [runOnServerThread](run-on-server-thread.md) | `fun runOnServerThread(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)` = 0L, block: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): BukkitTask`<br>Start a task run on main thread. Returns a task that will run on the next server tick. |
