package org.harundemir.taskmanager.features.addNewTask.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.harundemir.taskmanager.features.addNewTask.viewmodels.AddNewTaskViewModel

@Composable
fun AddNewTaskBody(
    innerPadding: PaddingValues,
    navController: NavController?,
    viewModel: AddNewTaskViewModel = viewModel()
) {
    val context = LocalContext.current
    val title = viewModel.title.value
    val description = viewModel.description.value

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
        }
        AddNewTaskSaveButton {
            if (viewModel.addNewTask(context, title, description)) {
                navController?.navigateUp()
            }
        }
    }
}