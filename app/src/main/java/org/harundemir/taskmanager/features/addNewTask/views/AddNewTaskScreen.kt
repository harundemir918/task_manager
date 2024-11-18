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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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

@Composable
fun AddNewTaskAppBar(navController: NavController?) {
    CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.primary,
    ), navigationIcon = {
        IconButton(onClick = { navController?.navigateUp() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "Go Back"
            )
        }
    }, title = { Text(text = "New Task") })
}

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

@Composable
fun AddNewTaskTextField(
    label: String, isMultiline: Boolean = false, text: String, onTextChanged: (String) -> Unit
) {
    var isFocused = remember { mutableStateOf(false) }

    TextField(
        label = { Text(text = label) },
        value = text,
        onValueChange = onTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = if (isFocused.value) 2.dp else 0.dp,
                    color = if (isFocused.value) MaterialTheme.colorScheme.primary
                    else Color.Transparent
                ), shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
            .onFocusChanged { isFocused.value = it.isFocused },
        singleLine = !isMultiline,
        minLines = if (isMultiline) 10 else 0,
        maxLines = if (isMultiline) 10 else 0,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun AddNewTaskSaveButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) { Text(text = "Save Task") }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun AddNewTaskScreenPreview() {
    AddNewTaskScreen(navController = null)
}