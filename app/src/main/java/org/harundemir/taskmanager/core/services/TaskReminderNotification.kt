package org.harundemir.taskmanager.core.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import org.harundemir.taskmanager.R

class TaskReminderNotification(
    private val context: Context
) {
    val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "task_reminder_notification"
    val name = "Task Reminder Notification"
    val descriptionText = "Task Reminder Notification"
    val importance = NotificationManager.IMPORTANCE_HIGH
    val channel = NotificationChannel(channelId, name, importance).apply {
        description = descriptionText
    }

    fun createNotificationChannel() {
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(title: String, message: String) {
        var builder = NotificationCompat.Builder(context, "task_reminder_notification")
            .setSmallIcon(R.drawable.baseline_alarm_24)
            .setContentTitle("Task Reminder")
            .setContentText("This is your task: $title")
            .setStyle(NotificationCompat.BigTextStyle()
            .bigText("Task: $title\nDescription:$message"))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        notificationManager.notify(1, builder.build())
    }
}