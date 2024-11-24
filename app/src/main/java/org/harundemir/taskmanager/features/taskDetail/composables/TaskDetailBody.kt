package org.harundemir.taskmanager.features.taskDetail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.harundemir.taskmanager.core.data.taskList

@Composable
fun TaskDetailBody(index: Int, innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        TaskDetailRow(title = "Title", value = taskList[index].title)
        TaskDetailRow(title = "Description", value = taskList[index].description)
        TaskDetailRow(title = "Due Date", value = taskList[index].dueDate)
        TaskDetailRow(title = "Completed", value = if (taskList[index].isCompleted) "Yes" else "No")
    }
}