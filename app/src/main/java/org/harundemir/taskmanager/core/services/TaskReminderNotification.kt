package org.harundemir.taskmanager.core.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import org.harundemir.taskmanager.MainActivity
import org.harundemir.taskmanager.R

class TaskReminderNotification(
    private val context: Context,
    taskId: Int = 0,
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

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        putExtra("destination", "taskDetail/$taskId")
    }
    val pendingIntent = PendingIntent.getActivity(
        context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    fun showNotification(title: String, message: String) {
        var builder = NotificationCompat.Builder(context, "task_reminder_notification")
            .setSmallIcon(R.drawable.logo_outlined)
            .setContentTitle("Task Reminder")
            .setContentText("This is your task: $title")
            .setStyle(
                NotificationCompat.BigTextStyle().bigText("Task: $title\nDescription: $message ")
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        notificationManager.notify(1, builder.build())
    }
}