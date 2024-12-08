package org.harundemir.taskmanager.features.auth.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginOtherOptionsText() {
    Text(text = "or login with", modifier = Modifier.padding(vertical = 16.dp))
}