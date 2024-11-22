package org.harundemir.taskmanager.features.home.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import org.harundemir.taskmanager.core.data.taskList
import org.harundemir.taskmanager.core.models.Task
import org.harundemir.taskmanager.features.home.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenListTile(task: Task) {
    val homeViewModel: HomeViewModel = viewModel()
    var isRemoved = remember { mutableStateOf(false) }
    val dismissState = rememberSwipeToDismissBoxState(confirmValueChange = {
        if (it == SwipeToDismissBoxValue.EndToStart) {
            isRemoved.value = true
            true
        } else {
            false
        }
    })
    val animationDuration: Int = 500
    var completed = remember { mutableStateOf(task.isCompleted) }
    var decoration = remember {
        mutableStateOf(
            if (completed.value) TextDecoration.LineThrough
            else TextDecoration.None
        )
    }

    LaunchedEffect(key1 = isRemoved.value) {
        if (isRemoved.value) {
            delay(animationDuration.toLong())
            homeViewModel.removeTask(task)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved.value, exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration), shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(state = dismissState,
            modifier = Modifier.padding(8.dp),
            enableDismissFromStartToEnd = false,
            backgroundContent = { HomeScreenDismissBackground(dismissState) },
            content = {
                Box(
                    modifier = Modifier
                        .clip(
                            shape = RoundedCornerShape(8.dp)
                        )
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .wrapContentHeight(),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(checked = completed.value, onCheckedChange = {
                                completed.value = it
                                taskList.find { it.id == task.id }?.isCompleted = it
                                decoration.value = if (completed.value) TextDecoration.LineThrough
                                else TextDecoration.None
                            })
                            Text(
                                text = task.title, style = TextStyle(
                                    textDecoration = decoration.value
                                )
                            )
                        }
                        Text(text = task.dueDate)
                    }
                }

            })
    }


}