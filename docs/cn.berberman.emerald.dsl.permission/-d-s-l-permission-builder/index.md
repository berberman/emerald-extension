[emerald-extension](../../index.md) / [cn.berberman.emerald.dsl.permission](../index.md) / [DSLPermissionBuilder](.)

# DSLPermissionBuilder

`class DSLPermissionBuilder`

A DSL structure to build permissions.

**Author**
berberman

**See Also**

[org.bukkit.permissions.Permission](#)

### Properties

| Name | Summary |
|---|---|
| [defaultValue](default-value.md) | `var defaultValue: PermissionDefault`<br>the defaultValue of permission, default is OP. |
| [description](description.md) | `var description: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>the description of permission, default is empty. |

### Functions

| Name | Summary |
|---|---|
| [childPermission](child-permission.md) | `fun childPermission(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, block: DSLPermissionBuilder.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)` = {}): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Build a child permission |
