@file:OptIn(ExperimentalMaterial3Api::class)

package org.harundemir.taskmanager.features.addNewTask.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddNewTaskScreen(navController: NavController?) {
    Scaffold(
        topBar = {
            AddNewTaskAppBar(navController)
        }, modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        AddNewTaskBody(innerPadding)
    }
}

@Composable
fun AddNewTaskAppBar(navController: NavController?) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = { navController?.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Go Back"
                )
            }
        },
        title = { Text(text = "New Task") }
    )
}

@Composable
fun AddNewTaskBody(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            AddNewTaskTextField(label = "Title")
            AddNewTaskTextField(label = "Description", isMultiline = true)
        }
        AddNewTaskSaveButton()
    }
}

@Composable
fun AddNewTaskTextField(label: String, isMultiline: Boolean = false) {
    var text = remember { mutableStateOf("") }
    var isFocused = remember { mutableStateOf(false) }

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = if (isFocused.value) 2.dp else 0.dp,
                    color =
                    if (isFocused.value)
                        MaterialTheme.colorScheme.primary
                    else Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
            .onFocusChanged { isFocused.value = it.isFocused },
        singleLine = !isMultiline,
        minLines = if (isMultiline) 10 else 0,
        maxLines = if (isMultiline) 10 else 0,
        shape = RoundedCornerShape(8.dp),
        label = { Text(text = label) },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun AddNewTaskSaveButton() {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), shape = RoundedCornerShape(8.dp)
    ) { Text(text = "Save Task") }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun AddNewTaskScreenPreview() {
    AddNewTaskScreen(navController = null)
}