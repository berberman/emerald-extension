@file:JvmName("LoggerUtil")

package cn.berberman.emerald.extension

import cn.berberman.emerald.Emerald
import cn.berberman.emerald.util.EmeraldUtil


/**
 * Log a INFO message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the INFO message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the isRegistered output
 * Handler objects.
 * <p>
 * @param  msg   The string message (or a key in the message catalog)
 */
fun info(msg: Any) = EmeraldUtil.logger.info(msg.toString())

/**
 * Log a INFO message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the INFO message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the isRegistered output
 * Handler objects.
 * <p>
 * @param  supplier   A function, which when called, produces the
 *                        desired log message
 */
inline fun info(supplier: () -> Any) = info(supplier())

/**
 * Log a WARNING message.
 * <p>
 * If the logger is currently enabled for the WARNING message
 * level then the given message is forwarded to all the
 * isRegistered output Handler objects.
 * <p>
 * @param   msg     The string message (or a key in the message catalog)
 */
fun warning(msg: Any) = EmeraldUtil.logger.warning(msg.toString())

/**
 * Log a WARNING message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the WARNING message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the isRegistered output
 * Handler objects.
 * <p>
 * @param   supplier   A function, which when called, produces the
 *                        desired log message
 */
inline fun warning(supplier: () -> Any) = warning(supplier())

fun debug(msg: Any) =
		if (Emerald.debug)
			EmeraldUtil.logger.warning("[debug] $msg")
		else Unit

inline fun debug(supplier: () -> Any) = debug(supplier())