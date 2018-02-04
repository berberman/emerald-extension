# Emerald Extension
![233](https://d1u5p3l4wpay3k.cloudfront.net/minecraft_zh_gamepedia/6/6a/Emerald.png?version=c18f3d42d9893b84e783362697408421)

[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Build Status](https://travis-ci.org/berberman/emerald-extension.svg?branch=master)](https://travis-ci.org/berberman/emerald-extension)
  [ ![Download](https://api.bintray.com/packages/berberman/maven/emerald-extension/images/download.svg) ](https://bintray.com/berberman/maven/emerald-extension/_latestVersion)

An extension of Minecraft-Spigot Plugin.   

What does this extension include?
* top-level member to access Spigot API easily.
* `async {}`,`runOnServerThread {}` etc. These functions are used to create bukkit task, which let you dispatch tasks in appropriate thread context.
* overload operators of classes `Location` and `Vector`, take place of `multiply`, `subtract`, `divide` etc. Also, you can get a value whatever which direction it's, such as  x, y or z by using `[]` operator.
* packing NBT values of `ItemStack`, allows you to use `ItemStack.modifyNBT {}` to create a DSL structure to edit NBT value efficiently.

## Example
```kotlin
ItemStack(Material.DIAMOND_SWORD).modifyNBT {
	type = NBTModifier.NBTTagModifier.NBTType.AttackDamage
	amount = 233
	slot = NBTModifier.NBTTagModifier.Slot.MainHand
}
```
Then you will get a `233-attack-damage` diamond sword.
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
### Gradle
```groovy
repositories {
    jcenter()
}
dependencies {
    compile "cn.berberman:emerald-extension:1.0"
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
  <version>1.0</version>
  <type>pom</type>
</dependency>
```
### Other
if you don't use dependency management system, you can download jars from [bintray](https://bintray.com/berberman/maven/emerald-extension/_latestVersion) or [github](https://github.com/berberman/emerald-extension/releases).
## Contribution
Welcome to open issues and pull request.
