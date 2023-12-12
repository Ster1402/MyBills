package com.sterdevs.mybills.features.settings.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sterdevs.mybills.core.ui.events.validations.ValidationEvent
import com.sterdevs.mybills.core.ui.states.AppGlobalState
import com.sterdevs.mybills.core.ui.utils.UiEventListener
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.FieldValidationUseCases
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateName
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePhoneNumber
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateRepeatedPassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateUsername
import com.sterdevs.mybills.features.settings.domain.use_cases.SettingsUseCases
import com.sterdevs.mybills.features.settings.ui.events.SettingsEvent
import com.sterdevs.mybills.features.settings.ui.states.PersonalInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    fieldValidationUseCases: FieldValidationUseCases,
    private val settingsUseCases: SettingsUseCases
) : ViewModel(), UiEventListener<SettingsEvent> {

    private val validateName: ValidateName = fieldValidationUseCases.validateName
    private val validateUsername: ValidateUsername = fieldValidationUseCases.validateUsername
    private val validatePhoneNumber: ValidatePhoneNumber =
        fieldValidationUseCases.validatePhoneNumber
    private val validatePassword: ValidatePassword = fieldValidationUseCases.validatePassword
    private val validateRepeatedPassword: ValidateRepeatedPassword =
        fieldValidationUseCases.validateRepeatedPassword

    private val _validationEvent = MutableStateFlow<ValidationEvent>(ValidationEvent.Idle)
    val validationEvent = _validationEvent.asStateFlow()

    private var _personalInfoState: MutableStateFlow<PersonalInfoState> = MutableStateFlow(
        PersonalInfoState(
            name = AppGlobalState.userState.value?.name ?: "",
            username = AppGlobalState.userState.value?.username ?: "",
            phoneNumber = AppGlobalState.userState.value?.phoneNumber ?: "",
        )
    )
    val personalInfoState: StateFlow<PersonalInfoState>
        get() = _personalInfoState.asStateFlow()

    override fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.NameChanged -> {
                _personalInfoState.value = _personalInfoState.value.copy(
                    name = event.name,
                    nameError = validateName.execute(event.name).error?.errorMessage
                )
            }

            is SettingsEvent.UsernameChanged -> {
                _personalInfoState.value = _personalInfoState.value.copy(
                    username = event.username,
                    usernameError = validateUsername.execute(event.username).error?.errorMessage
                )
            }

            is SettingsEvent.PhoneNumberChanged -> {
                _personalInfoState.value = _personalInfoState.value.copy(
                    phoneNumber = event.phoneNumber,
                    phoneNumberError = validatePhoneNumber.execute(event.phoneNumber).error?.errorMessage
                )
            }

            is SettingsEvent.OldPasswordChanged -> {
                var passwordError = validatePassword.execute(event.oldPassword).error?.errorMessage

                if (event.oldPassword.isEmpty()) {
                    passwordError = null
                }

                _personalInfoState.value = _personalInfoState.value.copy(
                    oldPassword = event.oldPassword,
                    oldPasswordError = passwordError
                )
            }

            is SettingsEvent.NewPasswordChanged -> {
                var passwordError = validatePassword.execute(event.newPassword).error?.errorMessage

                if (event.newPassword.isEmpty() && _personalInfoState.value.oldPassword.isEmpty()) {
                    passwordError = null
                }

                _personalInfoState.value = _personalInfoState.value.copy(
                    newPassword = event.newPassword,
                    newPasswordError = passwordError
                )
            }

            is SettingsEvent.RepeatedPasswordChanged -> {
                _personalInfoState.value = _personalInfoState.value.copy(
                    repeatedPassword = event.repeatedPassword,
                    repeatedPasswordError = validateRepeatedPassword.execute(
                        password = personalInfoState.value.newPassword,
                        repeatedPassword = event.repeatedPassword
                    ).error?.errorMessage
                )
            }

            is SettingsEvent.PersonalInformationSubmit -> {
                updatePersonalInformation()
            }
        }
    }

    private fun updatePersonalInformation() {
        // 1- Validate the fields

        // 2- Update the user information using the UseCase : UpdateUserUseCase
        try {
            settingsUseCases.updateUserUseCase.execute() //TODO
        } catch (e: Exception) {
            viewModelScope.launch {
                emitValidationEvent(
                    ValidationEvent.Failed.setReason("Sorry an error occur.\nWe can't update the user information.")
                )
            }
        }
    }

    override fun emitValidationEvent(event: ValidationEvent) {
        _validationEvent.value = event
    }
}