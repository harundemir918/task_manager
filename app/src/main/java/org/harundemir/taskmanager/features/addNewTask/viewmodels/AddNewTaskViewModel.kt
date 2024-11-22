package org.harundemir.taskmanager.features.addNewTask.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.harundemir.taskmanager.core.data.taskList
import org.harundemir.taskmanager.core.models.Task
import org.harundemir.taskmanager.core.util.DateTimeEnum
import org.harundemir.taskmanager.core.util.DateUtils

class AddNewTaskViewModel : ViewModel() {
    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _description = mutableStateOf("")
    val description: State<String> = _description

    private val _dueDate =
        mutableStateOf(DateUtils().formatDateTimeToString(type = DateTimeEnum.Date))
    val dueDate: State<String> = _dueDate

    private val _dueTime =
        mutableStateOf(DateUtils().formatDateTimeToString(type = DateTimeEnum.Time))
    val dueTime: State<String> = _dueTime

    fun updateTitle(text: String) {
        _title.value = text
    }

    fun updateDescription(text: String) {
        _description.value = text
    }

    fun updateDueDate(text: String) {
        _dueDate.value = text
    }

    fun updateDueTime(text: String) {
        _dueTime.value = text
    }

    fun addNewTask(
        context: Context,
        title: String,
        description: String,
        dueDate: String,
        dueTime: String
    ): Boolean {
        val task: Task = Task(
            id = taskList.size + 1,
            title = title,
            description = description,
            createdAt = DateUtils().formatDateTimeToString(),
            dueDate = "$dueDate $dueTime"
        )

        try {
            taskList.add(task)
            Toast.makeText(context, "Task added.", Toast.LENGTH_SHORT).show()
            return true
        } catch (e: Exception) {
            Toast.makeText(context, "There is an error: $e", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}