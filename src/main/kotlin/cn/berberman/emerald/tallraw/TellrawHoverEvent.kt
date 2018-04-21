package cn.berberman.emerald.tallraw

class TellrawHoverEvent(var action: Action = Action.SHOW_TEXT
                        , val value: MutableList<TellrawMessage> = mutableListOf()) {

	enum class Action {
		SHOW_TEXT,
		SHOW_ACHIEVEMENT,
		SHOW_ITEM,
		SHOW_ENTITY
	}

	operator fun MutableList<TellrawMessage>.plusAssign(tellrawMessage: TellrawMessage) {
		add(tellrawMessage)
	}

	operator fun MutableList<TellrawMessage>.minusAssign(tellrawMessage: TellrawMessage) {
		remove(tellrawMessage)
	}
}