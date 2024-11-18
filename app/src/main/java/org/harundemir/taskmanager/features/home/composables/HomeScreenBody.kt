package org.harundemir.taskmanager.features.home.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.harundemir.taskmanager.core.data.taskList

@Composable
fun HomeScreenBody(innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = innerPadding
    ) {
        items(items = taskList, key = { it.id }) { item -> HomeScreenListTile(item) }
    }
}