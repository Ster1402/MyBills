package com.sterdevs.mybills.features.authentication.ui.states

data class RegistrationFormState(
    val name: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val nameError: String? = null,
    val usernameError: String? = null,
    val phoneNumberError: String? = null,
    val passwordError: String? = null,
    val repeatedPasswordError: String? = null,
)
