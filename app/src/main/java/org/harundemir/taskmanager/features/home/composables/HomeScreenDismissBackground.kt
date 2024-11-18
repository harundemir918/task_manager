@file:OptIn(ExperimentalMaterial3Api::class)

package org.harundemir.taskmanager.features.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreenDismissBackground(dismissState: SwipeToDismissBoxState) {
    val color = if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
        Color.Red
    } else {
        Color.Transparent
    }

    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(8.dp)
            )
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            Icons.Default.Delete, tint = Color.White, contentDescription = "Delete"
        )
    }
}