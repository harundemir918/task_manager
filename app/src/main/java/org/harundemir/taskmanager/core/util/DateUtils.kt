package org.harundemir.taskmanager.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateUtils {
    fun getCurrentDate(): String {
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")
        val current = LocalDateTime.now().format(formatter)

        return current
    }
}