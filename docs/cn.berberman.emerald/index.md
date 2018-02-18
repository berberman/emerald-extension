---
title: cn.berberman.emerald - emerald-extension
---

[emerald-extension](../index.html) / [cn.berberman.emerald](.)

## Package cn.berberman.emerald

### Types

| [Emerald](-emerald/index.html) | `object Emerald`<br>Main class of this extension. |
| [TestPlugin](-test-plugin/index.html) | `class TestPlugin : JavaPlugin` |

### Type Aliases

| [猪](猪.html) | `typealias 猪 = Pig` |
| [玩家](玩家.html) | `typealias 玩家 = Player` |
| [玩家加入事件](玩家加入事件.html) | `typealias 玩家加入事件 = PlayerJoinEvent` |
| [鸡](鸡.html) | `typealias 鸡 = Chicken` |

### Extensions for External Classes

| [org.bukkit.World](org.bukkit.-world/index.html) |  |
| [org.bukkit.command.CommandSender](org.bukkit.command.-command-sender/index.html) |  |
| [org.bukkit.entity.Damageable](org.bukkit.entity.-damageable/index.html) |  |
| [org.bukkit.entity.Player](org.bukkit.entity.-player/index.html) |  |
| [org.bukkit.event.player.PlayerJoinEvent](org.bukkit.event.player.-player-join-event/index.html) |  |

### Properties

| [执行失败](执行失败.html) | `const val 执行失败: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [执行成功](执行成功.html) | `const val 执行成功: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| [registerCommands](register-commands.html) | `fun registerCommands(block: `[`DSLCommandScope`](../cn.berberman.emerald.dsl.command/-d-s-l-command-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Register commands. |
| [registerPermissions](register-permissions.html) | `fun registerPermissions(block: `[`DSLPermissionScope`](../cn.berberman.emerald.dsl.permission/-d-s-l-permission-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Register permissions. |
| [动作](动作.html) | `fun `[`DSLCommandBuilder`](../cn.berberman.emerald.dsl.command/-d-s-l-command-builder/index.html)`.动作(block: (CommandSender, `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [取消注册事件](取消注册事件.html) | `fun <T : Event> 取消注册事件(事件监听器: `[`PackingEvent`](../cn.berberman.emerald.dsl.event/-packing-event/index.html)`<T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [否则](否则.html) | `infix fun `[`TargetAndSenderBlocksData`](../cn.berberman.emerald.dsl.command/-d-s-l-command-builder/-target-and-sender-blocks-data/index.html)`.否则(block: CommandSender.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [启动协程](启动协程.html) | `fun 启动协程(context: `[`CoroutineContext`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines.experimental/-coroutine-context/index.html)`, block: suspend CoroutineScope.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): Job` |
| [启动异步](启动异步.html) | `fun 启动异步(延迟: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, block: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): BukkitTask` |
| [告诉我插件是啥](告诉我插件是啥.html) | `fun `[`Emerald`](-emerald/index.html)`.告诉我插件是啥(插件: JavaPlugin): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [命令](命令.html) | `fun `[`DSLCommandScope`](../cn.berberman.emerald.dsl.command/-d-s-l-command-scope/index.html)`.命令(名字: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, block: `[`DSLCommandBuilder`](../cn.berberman.emerald.dsl.command/-d-s-l-command-builder/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [在服务器线程运行](在服务器线程运行.html) | `fun 在服务器线程运行(block: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): BukkitTask` |
| [如果发送者是](如果发送者是.html) | `fun <T : CommandSender> `[`DSLCommandBuilder`](../cn.berberman.emerald.dsl.command/-d-s-l-command-builder/index.html)`.如果发送者是(发送者: CommandSender, block: T.() -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`TargetAndSenderBlocksData`](../cn.berberman.emerald.dsl.command/-d-s-l-command-builder/-target-and-sender-blocks-data/index.html) |
| [子命令](子命令.html) | `fun `[`DSLCommandBuilder`](../cn.berberman.emerald.dsl.command/-d-s-l-command-builder/index.html)`.子命令(名字: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, block: (CommandSender, `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>) -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [注册事件](注册事件.html) | `fun <T : Event> 注册事件(事件监听器: `[`PackingEvent`](../cn.berberman.emerald.dsl.event/-packing-event/index.html)`<T>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [注册命令](注册命令.html) | `fun 注册命令(block: `[`DSLCommandScope`](../cn.berberman.emerald.dsl.command/-d-s-l-command-scope/index.html)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [生成事件监听器](生成事件监听器.html) | `fun <T : Event> 生成事件监听器(eventPriority: EventPriority = EventPriority.NORMAL, block: T.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`PackingEvent`](../cn.berberman.emerald.dsl.event/-packing-event/index.html)`<T>` |

