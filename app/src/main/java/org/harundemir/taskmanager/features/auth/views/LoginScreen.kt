package org.harundemir.taskmanager.features.auth.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.harundemir.taskmanager.features.auth.composables.LoginScreenBody
import org.harundemir.taskmanager.features.auth.viewmodels.AuthViewModel

@Composable
fun LoginScreen(navController: NavController?) {
    val authViewModel: AuthViewModel = viewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LoginScreenBody(innerPadding, navController, authViewModel)

    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = null)
}