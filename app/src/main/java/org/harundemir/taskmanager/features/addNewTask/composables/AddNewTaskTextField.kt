package org.harundemir.taskmanager.features.addNewTask.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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