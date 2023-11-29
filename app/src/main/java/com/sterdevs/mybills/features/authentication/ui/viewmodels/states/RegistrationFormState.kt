package com.sterdevs.mybills.features.authentication.ui.viewmodels.states

data class RegistrationFormState(
    val username: String,
    val phoneNumber: String,
    val password: String,
    val repeatedPassword: String,

    val usernameError: String? = null,
    val phoneNumberError: String? = null,
    val passwordError: String? = null,
    val repeatedPasswordError: String? = null,
)
