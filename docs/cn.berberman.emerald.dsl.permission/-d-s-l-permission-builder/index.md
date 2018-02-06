---
title: DSLPermissionBuilder - emerald-extension
---

[emerald-extension](../../index.html) / [cn.berberman.emerald.dsl.permission](../index.html) / [DSLPermissionBuilder](.)

# DSLPermissionBuilder

`class DSLPermissionBuilder`

A DSL structure to build permissions.

**Author**
berberman

**See Also**

[org.bukkit.permissions.Permission](#)

### Properties

| [defaultValue](default-value.html) | `var defaultValue: PermissionDefault`<br>the defaultValue of permission, default is OP. |
| [description](description.html) | `var description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>the description of permission, default is empty. |

### Functions

| [childPermission](child-permission.html) | `fun childPermission(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, block: DSLPermissionBuilder.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Build a child permission |

