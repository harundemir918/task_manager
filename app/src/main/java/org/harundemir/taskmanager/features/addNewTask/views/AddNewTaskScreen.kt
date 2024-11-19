@file:OptIn(ExperimentalMaterial3Api::class)

package org.harundemir.taskmanager.features.addNewTask.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.harundemir.taskmanager.features.addNewTask.composables.AddNewTaskAppBar
import org.harundemir.taskmanager.features.addNewTask.composables.AddNewTaskBody
import org.harundemir.taskmanager.features.addNewTask.viewmodels.AddNewTaskViewModel

@Composable
fun AddNewTaskScreen(navController: NavController?) {
    val addNewTaskViewModel: AddNewTaskViewModel = viewModel()

    Scaffold(
        topBar = {
            AddNewTaskAppBar(navController)
        }, modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        AddNewTaskBody(innerPadding, navController, addNewTaskViewModel)
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun AddNewTaskScreenPreview() {
    AddNewTaskScreen(navController = null)
}