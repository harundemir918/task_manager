@file:OptIn(ExperimentalMaterial3Api::class)

package org.harundemir.taskmanager.features.addNewTask.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.harundemir.taskmanager.core.util.DateTimeEnum
import org.harundemir.taskmanager.core.util.DateUtils
import org.harundemir.taskmanager.features.addNewTask.viewmodels.AddNewTaskViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Calendar

@Composable
fun AddNewTaskBody(
    innerPadding: PaddingValues,
    navController: NavController?,
    viewModel: AddNewTaskViewModel = viewModel()
) {
    val context = LocalContext.current
    val openDatePickerModal = remember { mutableStateOf(false) }
    val openTimePickerModal = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    val title = viewModel.title.value
    val description = viewModel.description.value
    val dueDate = viewModel.dueDate.value
    val dueTime = viewModel.dueTime.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            AddNewTaskTextField(
                label = "Title", text = title,
                onTextChanged = {
                    viewModel.updateTitle(it)
                },
            )
            AddNewTaskTextField(
                label = "Description",
                text = description,
                isMultiline = true,
                onTextChanged = {
                    viewModel.updateDescription(it)
                },
            )
            AddNewTaskDueDate(
                dueDate = dueDate,
                onClick = {
                    openDatePickerModal.value = true
                },
            )
            AddNewTaskDueTime(
                dueTime = dueTime,
                onClick = {
                    openTimePickerModal.value = true
                },
            )
        }
        AddNewTaskSaveButton {
            if (viewModel.addNewTask(context, title, description, dueDate, dueTime)) {
                navController?.navigateUp()
            }
        }
        if (openDatePickerModal.value) {
            AddNewTaskDatePicker(state = datePickerState, onDateSelected = {
                viewModel.updateDueDate(
                    DateUtils().convertMillisToDate(
                        it ?: LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
                        type = DateTimeEnum.Date
                    )
                )
                openDatePickerModal.value = false
            }, onDismiss = { openDatePickerModal.value = false })
        }
        if (openTimePickerModal.value) {
            AddNewTaskTimePicker(
                state = timePickerState,
                onConfirm = { time ->
                    viewModel.updateDueTime(
                        (if (time != null) DateUtils().formatTimeToString(
                            time.hour, time.minute
                        ) else DateUtils().formatDateTimeToString(
                            type = DateTimeEnum.Time
                        )).toString()
                    )
                    openTimePickerModal.value = false
                },
                onDismiss = { openTimePickerModal.value = false },
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun AddNewTaskBodyPreview() {
    AddNewTaskBody(innerPadding = PaddingValues(8.dp), navController = null)
}