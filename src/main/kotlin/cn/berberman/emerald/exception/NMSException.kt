package cn.berberman.emerald.exception

/**
 * Throws when interact with NMS class has error.
 * @author berberman
 * @see EmeraldException
 */
class NMSException : EmeraldException {

	constructor(msg: String) : super(msg)

	constructor(msg: String, cause: Throwable) : super(msg, cause)

	override val serialVersionUID = -8623538475698456753L
}