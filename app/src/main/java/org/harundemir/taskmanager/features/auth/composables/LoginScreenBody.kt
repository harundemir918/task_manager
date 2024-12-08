package org.harundemir.taskmanager.features.auth.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.harundemir.taskmanager.core.composables.TaskManagerLongButton
import org.harundemir.taskmanager.features.auth.viewmodels.AuthViewModel

@Composable
fun LoginScreenBody(
    innerPadding: PaddingValues,
    navController: NavController?,
    viewModel: AuthViewModel = viewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthTitle()
        Spacer(modifier = Modifier.height(24.dp))
        LoginForm(viewModel)
        Spacer(modifier = Modifier.height(24.dp))
        TaskManagerLongButton(title = "Login", onClick = {
            navController?.navigate("home")
        })
        LoginOtherOptionsText()
        AuthSignUpWithGoogleButton()
        Spacer(modifier = Modifier.height(24.dp))
        LoginDoNotHaveAccountText()
    }
}

@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_8")
@Composable
fun LoginScreenBodyPreview() {
    LoginScreenBody(innerPadding = PaddingValues(8.dp), navController = null)
}