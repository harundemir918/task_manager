package org.harundemir.taskmanager.features.auth.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthViewModel: ViewModel() {
    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    fun updateEmail(text: String) {
        _email.value = text
    }

    fun updatePassword(text: String) {
        _password.value = text
    }
}