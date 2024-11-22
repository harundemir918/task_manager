package org.harundemir.taskmanager.core.util

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

enum class DateTimeEnum {
    Date,
    Time,
    DateTime
}

class DateUtils {
    fun formatDateTimeToString(
        date: LocalDateTime = LocalDateTime.now(),
        type: DateTimeEnum = DateTimeEnum.DateTime
    ): String {
        val formatter = when (type) {
            DateTimeEnum.Date -> DateTimeFormatter.ofPattern("dd-MM-yyyy")
            DateTimeEnum.Time -> DateTimeFormatter.ofPattern("HH:mm")
            DateTimeEnum.DateTime -> DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        }
        return date.format(formatter)
    }

    fun formatTimeToString(hour: Int, minute: Int): String? {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return LocalTime.of(hour, minute).format(formatter)
    }

    fun convertMillisToDate(millis: Long, type: DateTimeEnum = DateTimeEnum.DateTime): String {
        val formatter = when (type) {
            DateTimeEnum.Date -> SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            DateTimeEnum.Time -> SimpleDateFormat("HH:mm", Locale.getDefault())
            DateTimeEnum.DateTime -> SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        }
        return formatter.format(Date(millis))
    }

    fun convertToTimestamp(dateTime: String): Long? {
        return try {
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
            val date = formatter.parse(dateTime)
            date?.time
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}