# Emerald Extension
![233](https://d1u5p3l4wpay3k.cloudfront.net/minecraft_zh_gamepedia/6/6a/Emerald.png?version=c18f3d42d9893b84e783362697408421)

[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
  [![Codacy Badge](https://api.codacy.com/project/badge/Grade/7b4f22765ae44af4bd84103daddb00c7)](https://www.codacy.com/app/berberman/emerald-extension?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=berberman/emerald-extension&amp;utm_campaign=Badge_Grade)
 [![berberman](https://img.shields.io/badge/powered_by-berberman-orange.svg)](https://github.com/berberman)
 [![gradle](https://img.shields.io/badge/gradle-4.4-brightgreen.svg)](https://gradle.org/)
  [ ![Download](https://api.bintray.com/packages/berberman/maven/emerald-extension/images/download.svg) ](https://bintray.com/berberman/maven/emerald-extension/_latestVersion)

CI|Building Status
:---|:---:
Travis CI|[![Build Status](https://travis-ci.org/berberman/emerald-extension.svg?branch=master)](https://travis-ci.org/berberman/emerald-extension)
CircleCI|[![CircleCI](https://circleci.com/gh/berberman/emerald-extension.svg?style=svg)](https://circleci.com/gh/berberman/emerald-extension)
AppVeyor|[![Build status](https://ci.appveyor.com/api/projects/status/o1x1hh6xx6koh4v0?svg=true)](https://ci.appveyor.com/project/berberman/emerald-extension)
An extension of Minecraft-Spigot Plugin.   


This dependency use library `spigot-1.12.2`,  but it supports other versions theoretically.

What does this extension include?
* top-level member to access Spigot API easily.
* `async {}`,`runOnServerThread {}` etc. These functions are used to create bukkit task, which let you dispatch tasks in appropriate thread context.
* overload operators of classes `Location` and `Vector`, take place of `multiply`, `subtract`, `divide` etc. Also, you can get a value whatever which direction it's, such as  x, y or z by using `[]` operator.
* packing NBT values of `ItemStack`, allows you to use `ItemStack.modifyNBT {}` to create a DSL structure to edit NBT value efficiently.
* DSL part, which allows you to register commands, permissions, events easily.
## Example
### Modify NBT
```kotlin
ItemStack(Material.DIAMOND_SWORD).modifyNBT {
	type = NBTModifier.NBTTagModifier.NBTType.AttackDamage
	amount = 233
	slot = NBTModifier.NBTTagModifier.Slot.MainHand
}
```
Then you will get a `233-attack-damage` diamond sword.
### Scheduler
```kotlin
Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, {
	runOnServerThread {
		Bukkit.getWorlds().flatMap { it.entities }.forEach {
			it.setGravity(false)
		}
	}
}, 5000)
```
Removing all entities' gravity per 5000 ticks. The operation to entity must run in the Spigot main thread, `runOnServerThread {}` will save you :)

## Usage
You need to import this dependency into your project, there are three ways:
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
### Other
if you don't use dependency management system, you can download jars from [bintray](https://bintray.com/berberman/maven/emerald-extension/_latestVersion) or [github](https://github.com/berberman/emerald-extension/releases).
## How to use?
Here is a sample:
```kotlin
override fun onEnable() {
  Emerald.setDebug(true)
  Emerald.registerContext(this)
  Emerald.registerCommands {
    command("test") {
      permissionMessage = "You don't have this permission!"
      permission = "test"
      description = "Test Command."
      usageMessage = "/test"
      action { sender ->
        whenSenderIs<Player>(sender) {
          this sendMessage "Hi! $name"
          true
        }()
      }
    }
    command("hello") {
      action { sender ->
        whenSenderIs<Player>(sender) {
          this sendMessage "Hi! $name"
          true
        } otherwise {
          this sendMessage "You are not a Player!"
          false
        }
      }
    }
  }
  Emerald.registerEvents {
    event<PlayerJoinEvent> {
      joinMessage = ChatColor.AQUA * "Hello,${player.name}!"
    }
  }
  Emerald.registerPermissions {
    permission("test") {
      description = "Test permission"
      childPermission("test.child") {
        description = "Test child permission"
        defaultValue = PermissionDefault.TRUE
      }
    }
  }
}
```
Default value of `Emerald.setDebug()` is false. While you are building commands, only `name` is required, other values are optional. `registerPermissions` is similar to `registerCommands`, both of them have optional arguments.  
 **Don't forget that if you use the function `whenSenderIs<T>()` without `otherwise`, you have to use a `()` to replace it.**
## Contribution
Welcome to open issues and make pull request.
