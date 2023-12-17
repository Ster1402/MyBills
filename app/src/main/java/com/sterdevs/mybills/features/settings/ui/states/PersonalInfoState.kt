package com.sterdevs.mybills.features.settings.ui.states

data class PersonalInfoState(
    val name: String = "",
    val username: String = "",
    val phoneNumber: String = "",
    val oldPassword: String = "",
    val newPassword: String = "",
    val repeatedPassword: String = "",

    val nameError: String? = null,
    val usernameError: String? = null,
    val phoneNumberError: String? = null,
    val oldPasswordError: String? = null,
    val newPasswordError: String? = null,
    val repeatedPasswordError: String? = null,
)
