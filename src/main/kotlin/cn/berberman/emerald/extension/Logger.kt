package cn.berberman.emerald.extension

import cn.berberman.emerald.util.EmeraldUtil


/**
 * Log a INFO message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the INFO message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the registered output
 * Handler objects.
 * <p>
 * @param  msg   The string message (or a key in the message catalog)
 */
fun info(msg: String) = EmeraldUtil.logger.info(msg)

/**
 * Log a INFO message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the INFO message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the registered output
 * Handler objects.
 * <p>
 * @param  supplier   A function, which when called, produces the
 *                        desired log message
 */
inline fun info(supplier: () -> String) = EmeraldUtil.logger.info(supplier())

/**
 * Log a WARNING message.
 * <p>
 * If the logger is currently enabled for the WARNING message
 * level then the given message is forwarded to all the
 * registered output Handler objects.
 * <p>
 * @param   msg     The string message (or a key in the message catalog)
 */
fun warning(msg: String) = EmeraldUtil.logger.warning(msg)

/**
 * Log a WARNING message, which is only to be constructed if the logging
 * level is such that the message will actually be logged.
 * <p>
 * If the logger is currently enabled for the WARNING message
 * level then the message is constructed by invoking the provided
 * supplier function and forwarded to all the registered output
 * Handler objects.
 * <p>
 * @param   supplier   A function, which when called, produces the
 *                        desired log message
 */
inline fun warning(supplier: () -> String) = EmeraldUtil.logger.warning(supplier())

