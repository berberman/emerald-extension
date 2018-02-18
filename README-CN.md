# Emerald Extension
![233](https://d1u5p3l4wpay3k.cloudfront.net/minecraft_zh_gamepedia/6/6a/Emerald.png?version=c18f3d42d9893b84e783362697408421)

[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7b4f22765ae44af4bd84103daddb00c7)](https://www.codacy.com/app/berberman/emerald-extension?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=berberman/emerald-extension&amp;utm_campaign=Badge_Grade)
[![berberman](https://img.shields.io/badge/powered_by-berberman-orange.svg)](https://github.com/berberman)
[![minecraft](https://img.shields.io/badge/minecraft-1.12.2-yellowgreen.svg)](https://www.spigotmc.org/)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/berberman/emerald-extension)
[![gradle](https://img.shields.io/badge/gradle-4.4-brightgreen.svg)](https://gradle.org/)
[![Download](https://api.bintray.com/packages/berberman/maven/emerald-extension/images/download.svg)](https://bintray.com/berberman/maven/emerald-extension/_latestVersion)

持续集成构建|构建状态
:---|:---:
Travis CI|[![Build Status](https://travis-ci.org/berberman/emerald-extension.svg?branch=master)](https://travis-ci.org/berberman/emerald-extension)
CircleCI|[![CircleCI](https://circleci.com/gh/berberman/emerald-extension.svg?style=svg)](https://circleci.com/gh/berberman/emerald-extension)
AppVeyor|[![Build status](https://ci.appveyor.com/api/projects/status/o1x1hh6xx6koh4v0?svg=true)](https://ci.appveyor.com/project/berberman/emerald-extension)
Wercker|[![wercker status](https://app.wercker.com/status/820a6a6b02261bd3bfe88b9b5a066dce/s/master "wercker status")](https://app.wercker.com/project/byKey/820a6a6b02261bd3bfe88b9b5a066dce)
Codeship|[ ![Codeship Status for berberman/emerald-extension](https://app.codeship.com/projects/350b4220-ec8e-0135-f54a-6e606d5d3d05/status?branch=master)](https://app.codeship.com/projects/270349)

一个作为 Spigot 插件开发的依赖。

本项目引入了 `spigot-1.12.2` 作为依赖, 理论支持 1.9+ 。

本项目内容：
* 一些顶层成员、函数，可以更便捷访问 Bukkit API
* Kotlin 协程对 Spigot的支持
* DSL 对物品的 NBT 参数修改
* DSL 注册命令
* DSL 注册、取消注册事件监听器
* DSL 注册权限
* `Location`,`Vector` 运算符的重载
* NMS `Player` 发包，只实现了发送 Action Bar

## 食用方法
本项目发布于 Bintray，~~Maven签名太麻烦~~ 只是一个依赖，并非前置插件，不能单独加载 / 运行，你需要先将其添加到项目依赖中。
### Gradle
```groovy
repositories {
    jcenter()
}
dependencies {
    compile "cn.berberman:emerald-extension:1.1"
}
```
### Maven
```xml
<repositories>
   <repository>
     <id>jcenter</id>
     <name>JCenter</name>
     <url>https://jcenter.bintray.com/</url>
   </repository>
</repositories>

<dependency>
  <groupId>cn.berberman</groupId>
  <artifactId>emerald-extension</artifactId>
  <version>1.1</version>
  <type>pom</type>
</dependency>
```
### 其他
如果你不使用依赖管理系统，
可以直接下载 Jar ([bintray](https://bintray.com/berberman/maven/emerald-extension/_latestVersion) ,
[github](https://github.com/berberman/emerald-extension/releases)) ，放到项目依赖中。
## 一些栗子
### 修改NBT
```kotlin
ItemStack(Material.WOOD_SWORD).modifyNBT {
    type = NBTModifier.NBTTagModifier.NBTType.AttackDamage
	amount = 233
	slot = NBTModifier.NBTTagModifier.Slot.MainHand
}
    .operateMeta {
  		isUnbreakable = true
  	    lore = lore.apply { add("蜜汁木剑") }
  	    }
```
一把 `不会损坏的233攻击力` 的木剑就到手了。
### 协程
```kotlin
launch(SchedulerThread) {
	coroutineContext[BukkitTaskCoroutineContext]?.taskId
	getDataFromServer()
}
suspend fun getDataFromServer(): String {
    val data = runBlocking {
	    delay(5000) //模拟数据获取
	    "233333"
	}
    return data
}
```
当 context 为 `SchedulerThread` 时，协程将在 Bukkit Scheduler Thread 上调度。
你可以从 context 获取该协程所对的 `BukkitTask` 实例。除了`SchedulerThread` 外，
还有 `ServerThread`，这会使协程运行在服务器主线程上 **（这个线程不能被阻塞！，例如 `Thread.sleep()`，
但协程的 `delay` 等挂起函数可以正常调用。 )** 。
当然，`async` 也同样接受这两个 context。
### Bukkit Scheduler
```kotlin
launch(SchedulerThread) {
	Thread.sleep(3000)
	runOnServerThread {
		player.world.spawnEntity<Pig>(player.location)
	}
}
```
当你执行一些会阻塞线程，并非挂起的任务时，需要使该协程在 `Scheduler` 线程上调度。
这时，如果你想访问 Bukkit API，你就需要使任务运行在服务器线程上。但是，一些同步执行的
简单语句没有开一个在主线程上运行的协程的必要，所以你可以通过`runOnServerThread {}` 函数
使其运行在服务器主线程上。
### 注册命令
```kotlin
registerCommands {
    command("test") {
		description = "A test command"
		addAlias("t")
		permission = "test.test"
		permissionMessage = "你没这权限！"
		action { sender ->
			sender sendMessage "Hi!"
			true
		}
		subCommand("player") { sender, args ->
			sender.whenSenderIs<Player> {
				world.spawnEntity<Pig>(location)
				sendMessage(args.joinToString())
				true
			} otherwise {
				sendMessage("你不是玩家！")
				false
			}
		}
	}
	command("xxx"){
		//...
	}
}
```
`registerCommands` 在插件启动时应该被调用。对于每个命令来说，除了名字必须声明外，其他都是可选的，
包括子命令、描述、权限、别名等。子命令可以有许多，但父命令的 `action` 只有在子命令未被调用 (而不是调用失败) 时调用。
~~前面那句话没卵用，请忽略。~~ 在使用 `whenSenderIs<T : CommandSender>` 时后面要加 `otherwise {}`并且声明返回值。
在 `registerCommands {}` 中，你可以使用 `command(name) {}` 注册许多命令。
### 注册事件监听器
```kotlin
registerCommands {
	command("aaa") {
		subCommand("a") { _, _ ->
			registerEvent {
				event<PlayerJoinEvent> {
					player sendMessage ChatColor.AQUA * "Hello,${player.name}!"
				}
			}
			true
		}
		val event = event<PlayerBedEnterEvent> {
			player sendMessage "欢迎上床♂"
		}
		subCommand("b") { _, _ ->
    		registerEvent(event)
			true
		}
		subCommand("c") { _, _ ->
			unregisterEvent(event)
			true
		}
		subCommand("d") { _, _ ->
			registerEvents {
				event<PlayerBedLeaveEvent> { }
				event<PlayerAdvancementDoneEvent> { }
				//...
			}
			true
		}
	}
}
```
一个事件监听器可以被动态地注册和取消注册，上面的栗子就是在调用命令时对事件监听器进行操作。
`registerEvents` 可以一次性注册多个事件监听器，如果想要取消注册的话就要把每个监听器的引用保存下来，
使用 `unregisterEvent()` 取消注册。
### 注册权限
```kotlin
registerPermissions {
	permission("test.test") {
		defaultValue = PermissionDefault.FALSE
		description = "谁都没有！"
		childPermission("a") {
			defaultValue = PermissionDefault.NOT_OP
			childPermission("xxx") {
				childPermission("xxx")
			}
		}
	}
}
```
和注册命令相似，`registerPermissions` 需要在插件启用时调用。
对于每一个权限来说，只有名字时必要的，其他都是可选的，你可以给子权限注册子权限注册子权限（逃。
## 贡献
求星星，欢迎提 Issue、发PR。
