package com.sterdevs.mybills.features.authentication.ui.events

sealed class LoginFormEvent {
    data class UsernameChanged(val username: String) : LoginFormEvent()
    data class PasswordChanged(val password: String) : LoginFormEvent()

    data object Submit : LoginFormEvent()
}