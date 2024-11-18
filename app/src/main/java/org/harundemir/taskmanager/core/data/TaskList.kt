package org.harundemir.taskmanager.core.data

import org.harundemir.taskmanager.core.models.Task
import org.harundemir.taskmanager.core.util.DateUtils

val taskList: MutableList<Task> = mutableListOf<Task>(
    Task(
        id = 1,
        title = "Sample task",
        description = "This is a sample task",
        createdAt = DateUtils().getCurrentDate(),
    ),
    Task(
        id = 2,
        title = "New task",
        description = "This is a new task",
        createdAt = DateUtils().getCurrentDate(),
    )
)