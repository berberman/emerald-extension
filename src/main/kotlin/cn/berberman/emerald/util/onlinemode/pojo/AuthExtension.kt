package cn.berberman.emerald.util.onlinemode.pojo

import cn.berberman.emerald.reflection.ReflectionClasses
import java.util.*

fun Property.toMojang() =
		ReflectionClasses.MojangAuthLib.Property()
				.getConstructor(String::class.java, String::class.java, String::class.java)
				.newInstance(name, value, signature)

fun ProfileName.toGameProfile(): Any =
		ReflectionClasses.MojangAuthLib.GameProfile()
				.getConstructor(UUID::class.java, String::class.java)
				.newInstance(UUID.fromString(uuid), name)
