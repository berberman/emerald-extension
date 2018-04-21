package cn.berberman.emerald.extension

import cn.berberman.emerald.nms.wrapper.chat.NmsChatSerializer
import cn.berberman.emerald.tallraw.TellrawMessage

fun tellrawMessage(text: String, block: TellrawMessage.() -> Unit = {}) =
		TellrawMessage(text).apply(block)

fun TellrawMessage.toIChatBaseComponent() =
		NmsChatSerializer.decodeFromString(toJsonString())

fun List<TellrawMessage>.toIChatBaseList() =
		map { NmsChatSerializer.decodeFromString(it.toJsonString()) }
