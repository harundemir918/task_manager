package org.harundemir.taskmanager.features.auth.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun LoginDoNotHaveAccountText() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Don't have an account? ")
        Text(
            text = "Register.",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            ),
            modifier = Modifier.clickable(enabled = true, onClick = {})
        )
    }
}