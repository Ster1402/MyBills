package com.sterdevs.mybills.features.settings.ui.events

open class SettingsEvent {
    data class NameChanged(val name: String): SettingsEvent()
    data class UsernameChanged(val username: String): SettingsEvent()
    data class PhoneNumberChanged(val phoneNumber: String): SettingsEvent()
    data class OldPasswordChanged(val oldPassword: String): SettingsEvent()
    data class NewPasswordChanged(val newPassword: String): SettingsEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String): SettingsEvent()

    data object PersonalInformationSubmit: SettingsEvent()
}