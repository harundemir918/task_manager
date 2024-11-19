package org.harundemir.taskmanager.features.home.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreenAddTaskButton(navController: NavController?) {
    FloatingActionButton(onClick = {
        navController?.navigate("addNewTask")
    }) {
        Icon(Icons.Filled.Add, "Add Task")
    }
}