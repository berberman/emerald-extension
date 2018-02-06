package cn.berberman.emerald.exception

/**
 * An abstract exception of emerald.
 *@author berberman
 */
abstract class EmeraldException : RuntimeException {
	constructor(msg: String) : super(msg)

	constructor(msg: String, cause: Throwable) : super(msg, cause)

	internal abstract val serialVersionUID: Long
}