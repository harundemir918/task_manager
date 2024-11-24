package org.harundemir.taskmanager.core.models

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val createdAt: String,
    val dueDate: String,
    var isCompleted: Boolean = false
)
