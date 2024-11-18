package org.harundemir.taskmanager.features.home.viewmodels

import androidx.lifecycle.ViewModel
import org.harundemir.taskmanager.core.data.taskList
import org.harundemir.taskmanager.core.models.Task

class HomeViewModel : ViewModel() {
    fun removeTask(task: Task) {
        taskList.remove(task)
    }
}