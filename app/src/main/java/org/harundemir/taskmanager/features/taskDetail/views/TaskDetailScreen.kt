package org.harundemir.taskmanager.features.taskDetail.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import org.harundemir.taskmanager.features.taskDetail.composables.TaskDetailAppBar
import org.harundemir.taskmanager.features.taskDetail.composables.TaskDetailBody

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(index: Int, navController: NavController?) {
    Scaffold(
        topBar = {
            TaskDetailAppBar(navController = navController)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding -> TaskDetailBody(index, innerPadding) }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun TaskDetailScreenPreview() {
    TaskDetailScreen(index = 0, navController = null)
}