package org.harundemir.taskmanager.features.auth.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import org.harundemir.taskmanager.core.composables.TaskManagerTextField
import org.harundemir.taskmanager.features.auth.viewmodels.AuthViewModel

@Composable
fun LoginForm(viewModel: AuthViewModel) {
    val email = viewModel.email.value
    val password = viewModel.password.value

    Column {
        TaskManagerTextField(
            label = "Email",
            text = email,
            onTextChanged = {
                viewModel.updateEmail(it)
            })
        TaskManagerTextField(
            label = "Password",
            text = password,
            isObscured = true,
            onTextChanged = {
                viewModel.updatePassword(it)
            })

    }
}