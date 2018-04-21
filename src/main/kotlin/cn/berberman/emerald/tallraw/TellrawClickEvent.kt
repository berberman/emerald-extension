package cn.berberman.emerald.tallraw

class TellrawClickEvent(var action: Action = Action.RUN_COMMAND
                        , var value: String = "") {

	enum class Action {
		OPEN_URL,
		OPEN_FILE,
		RUN_COMMAND,
		SUGGEST_COMMAND,
		CHANGE_PAGE;
	}

}