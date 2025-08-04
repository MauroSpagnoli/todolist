package msh.todolist.tools

import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date

class Log {
    companion object {
        private const val LOG_FILE_PATH = "/log_path/app.log"
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

        enum class LogLevel {
            DEBUG, INFO, WARNING, ERROR
        }

        private fun writeLog(level: LogLevel, message: String, tag: String? = null) {
            val timestamp = dateFormat.format(Date())
            val logTag = tag ?: getCallerClassName()
            val logEntry = "[$timestamp] [$level] [$logTag] $message\n"

            try {
                val logFile = File(LOG_FILE_PATH)
                logFile.parentFile?.mkdirs() // Crea los directorios si no existen
                FileWriter(logFile, true).use { writer ->
                    writer.append(logEntry)
                }
            } catch (e: Exception) {
                System.err.println("Error al escribir en el log: ${e.message}")
                System.err.print(logEntry) // Imprime en stderr como fallback
            }
        }

        private fun getCallerClassName(): String {
            val stackTrace = Thread.currentThread().stackTrace
            // El índice puede variar dependiendo de la profundidad de la llamada, ajusta si es necesario
            // StackTrace[0] es getStackTrace
            // StackTrace[1] es esta función (getCallerClassName)
            // StackTrace[2] es la función que llamó a d, i, w, o e (debug, info, etc.)
            // StackTrace[3] es la clase que realmente originó el log
            return if (stackTrace.size > 4) stackTrace[4].className.substringAfterLast('.') else "UnknownClass"
        }

        fun d(message: String, tag: String? = null) = writeLog(LogLevel.DEBUG, message, tag)
        fun i(message: String, tag: String? = null) = writeLog(LogLevel.INFO, message, tag)
        fun w(message: String, tag: String? = null) = writeLog(LogLevel.WARNING, message, tag)
        fun e(message: String, tag: String? = null) = writeLog(LogLevel.ERROR, message, tag)
        fun e(message: String, throwable: Throwable, tag: String? = null) {
            val fullMessage = "$message\n${throwable.stackTraceToString()}"
            writeLog(LogLevel.ERROR, fullMessage, tag)
        }
    }
}