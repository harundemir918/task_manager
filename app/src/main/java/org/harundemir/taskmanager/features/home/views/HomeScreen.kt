@file:OptIn(ExperimentalMaterial3Api::class)

package org.harundemir.taskmanager.features.home.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import org.harundemir.taskmanager.features.home.composables.HomeScreenAddTaskButton
import org.harundemir.taskmanager.features.home.composables.HomeScreenAppBar
import org.harundemir.taskmanager.features.home.composables.HomeScreenBody
import org.harundemir.taskmanager.ui.theme.TaskManagerTheme

@Composable
fun HomeScreen(navController: NavController?) {
    Scaffold(
        topBar = {
            HomeScreenAppBar()
        },
        floatingActionButton = { HomeScreenAddTaskButton(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HomeScreenBody(innerPadding = innerPadding)
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun HomeScreenPreview() {
    TaskManagerTheme {
        HomeScreen(navController = null)
    }
}