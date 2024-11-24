package org.harundemir.taskmanager.core.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val createdAt: String,
    val dueDate: String,
    var isCompleted: Boolean = false
) : Parcelable
