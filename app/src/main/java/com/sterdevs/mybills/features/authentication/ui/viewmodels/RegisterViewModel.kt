package com.sterdevs.mybills.features.authentication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sterdevs.mybills.core.domain.models.validations.ValidationEvent
import com.sterdevs.mybills.core.domain.models.viewmodels.IViewModels
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateName
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePhoneNumber
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateRepeatedPassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateUsername
import com.sterdevs.mybills.features.authentication.ui.events.RegistrationFormEvent
import com.sterdevs.mybills.features.authentication.ui.viewmodels.states.RegistrationFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val validateName: ValidateName = ValidateName(),
    private val validateUsername: ValidateUsername = ValidateUsername(),
    private val validatePhoneNumber: ValidatePhoneNumber = ValidatePhoneNumber(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword()
) : ViewModel(), IViewModels<RegistrationFormEvent> {

    private val _state = MutableStateFlow(RegistrationFormState())
    val state = _state.asStateFlow()

    private val _validationEvent = MutableStateFlow<ValidationEvent?>(null)
    val validationEvent = _validationEvent.asStateFlow()


    // Manage an event that occur in the IViewModels EventType (Here RegistrationFormEvent)
    override fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.NameChanged -> {
                _state.value = _state.value.copy(
                    name = event.name,
                    nameError = validateName.execute(event.name).error?.errorMessage
                )
            }

            is RegistrationFormEvent.UsernameChanged -> {
                _state.value = _state.value.copy(
                    username = event.username,
                    usernameError = validateUsername.execute(event.username).error?.errorMessage
                )
            }

            is RegistrationFormEvent.PhoneNumberChanged -> {
                _state.value = _state.value.copy(
                    phoneNumber = event.phoneNumber,
                    phoneNumberError = validatePhoneNumber.execute(event.phoneNumber).error?.errorMessage
                )
            }

            is RegistrationFormEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    passwordError = validatePassword.execute(event.password).error?.errorMessage
                )
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                _state.value = _state.value.copy(
                    repeatedPassword = event.repeatedPassword,
                    repeatedPasswordError = validateRepeatedPassword.execute(
                        password = state.value.password,
                        repeatedPassword = event.repeatedPassword
                    ).error?.errorMessage
                )
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val nameValidationResult = validateName.execute(state.value.name)
        val usernameValidationResult = validateUsername.execute(state.value.username)
        val phoneNumberValidationResult = validatePhoneNumber.execute(state.value.phoneNumber)
        val passwordValidationResult = validatePassword.execute(state.value.password)
        val repeatedPasswordValidationResult = validateRepeatedPassword.execute(
            password = state.value.password,
            repeatedPassword = state.value.repeatedPassword
        )

        val hasError = listOf(
            nameValidationResult,
            usernameValidationResult,
            phoneNumberValidationResult,
            passwordValidationResult,
            repeatedPasswordValidationResult
        ).any { !it.isSuccessful }

        // Check if there is an error
        if (hasError) {
            _state.value = _state.value.copy(
                nameError = nameValidationResult.error?.errorMessage,
                usernameError = usernameValidationResult.error?.errorMessage,
                phoneNumberError = phoneNumberValidationResult.error?.errorMessage,
                passwordError = passwordValidationResult.error?.errorMessage,
                repeatedPasswordError = repeatedPasswordValidationResult.error?.errorMessage
            )

            return
        }

        viewModelScope.launch {
            emitValidationEvent(ValidationEvent.Success)
        }

    }

    override fun emitValidationEvent(event: ValidationEvent) {
        _validationEvent.value = event
    }

}