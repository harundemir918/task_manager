package org.harundemir.taskmanager.core.models

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val createdAt: String,
    var isCompleted: Boolean = false
)
