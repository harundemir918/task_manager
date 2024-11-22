@file:OptIn(ExperimentalMaterial3Api::class)

package org.harundemir.taskmanager.features.addNewTask.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.harundemir.taskmanager.R
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
            AddNewTaskTextField(label = "Title", text = title, onTextChanged = {
                viewModel.updateTitle(it)
            })
            AddNewTaskTextField(
                label = "Description",
                text = description,
                isMultiline = true,
                onTextChanged = {
                    viewModel.updateDescription(it)
                },
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(text = "Due Date")
                    Text(text = dueDate)
                }
                IconButton(onClick = {
                    openDatePickerModal.value = true
                }) {
                    Icon(
                        Icons.Filled.DateRange,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Pick Date"
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(text = "Due Time")
                    Text(text = dueTime)
                }
                IconButton(onClick = {
                    openTimePickerModal.value = true
                }) {
                    Image(
                        painter = painterResource(R.drawable.baseline_alarm_24),
                        contentDescription = "Pick Time"
                    )
                }
            }
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
                            time.hour,
                            time.minute
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