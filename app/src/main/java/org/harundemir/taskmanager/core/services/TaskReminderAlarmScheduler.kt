package org.harundemir.taskmanager.core.services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build

class TaskReminderAlarmScheduler {
    fun scheduleTaskReminder(
        context: Context, taskTitle: String, taskMessage: String, dueTimeMillis: Long
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, TaskReminderReceiver::class.java).apply {
            putExtra("taskTitle", taskTitle)
            putExtra("taskMessage", taskMessage)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskTitle.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= 31 && alarmManager.canScheduleExactAlarms()) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, dueTimeMillis, pendingIntent
            )
        }
    }
}