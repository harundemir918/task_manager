package org.harundemir.taskmanager.features.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.harundemir.taskmanager.core.data.taskList
import org.harundemir.taskmanager.core.models.Task

@Composable
fun HomeScreenListTile(task: Task) {
    var completed = remember { mutableStateOf(task.isCompleted) }
    var decoration =
        remember {
            mutableStateOf(
                if (completed.value)
                    TextDecoration.LineThrough
                else
                    TextDecoration.None
            )
        }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = completed.value, onCheckedChange = {
                completed.value = it
                taskList.find { it.id == task.id}?.isCompleted = it
                decoration.value = if (completed.value)
                    TextDecoration.LineThrough
                else
                    TextDecoration.None
            })
            Text(
                text = task.title,
                style = TextStyle(
                    textDecoration = decoration.value
                )
            )
        }
        Text(text = task.createdAt)
    }
}