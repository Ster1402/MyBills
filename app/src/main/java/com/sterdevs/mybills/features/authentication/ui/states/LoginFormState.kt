package com.sterdevs.mybills.features.authentication.ui.states

data class LoginFormState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
)
