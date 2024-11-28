package org.harundemir.taskmanager.core.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TaskReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val taskId = intent?.getIntExtra("taskId", 0) ?: 0
        val taskTitle = intent?.getStringExtra("taskTitle") ?: "Task Reminder"
        val taskMessage =
            intent?.getStringExtra("taskMessage") ?: "It's time to complete your task!"

        TaskReminderNotification(context = context!!, taskId = taskId).showNotification(
            taskTitle,
            taskMessage
        )
    }
}