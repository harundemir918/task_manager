@file:OptIn(ExperimentalMaterial3Api::class)

package org.harundemir.taskmanager.features.home.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.harundemir.taskmanager.ui.theme.TaskManagerTheme

@Composable
fun HomeScreen(navController: NavController?) {
    Scaffold(
        topBar = {
            HomeScreenAppBar()
        },
        floatingActionButton = { AddTaskButton(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        HomeScreenTaskList(innerPadding = innerPadding)
    }
}

@Composable
fun HomeScreenAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(text = "Tasks")
        },
    )
}

@Composable
fun HomeScreenTaskList(innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = innerPadding
    ) {
        item {
            HomeScreenListTile()
            HomeScreenListTile()
            HomeScreenListTile()
            HomeScreenListTile()
            HomeScreenListTile()
            HomeScreenListTile()
        }
    }
}

@Composable
fun HomeScreenListTile() {
    var completed = remember { mutableStateOf(false) }
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
                decoration.value = if (completed.value)
                    TextDecoration.LineThrough
                else
                    TextDecoration.None
            })
            Text(
                text = "Hey, this is a sample task",
                style = TextStyle(
                    textDecoration = decoration.value
                )
            )
        }
        Text(text = "13:15")
    }
}

@Composable
fun AddTaskButton(navController: NavController?) {
    FloatingActionButton(onClick = {
        navController?.navigate("addNewTask")
    }) {
        Icon(Icons.Filled.Add, "Add Task")
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun HomeScreenPreview() {
    TaskManagerTheme {
        HomeScreen(navController = null)
    }
}