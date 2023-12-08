package com.sterdevs.mybills.features.authentication.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.ui.events.validations.ValidationEvent
import com.sterdevs.mybills.core.domain.use_cases.GetUserByUsernameUseCase
import com.sterdevs.mybills.core.ui.states.AppGlobalState
import com.sterdevs.mybills.core.ui.utils.UiEventListener
import com.sterdevs.mybills.features.authentication.domain.use_cases.AuthenticationUseCases
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.FieldValidationUseCases
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateName
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePhoneNumber
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateRepeatedPassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateUsername
import com.sterdevs.mybills.features.authentication.ui.events.RegistrationFormEvent
import com.sterdevs.mybills.features.authentication.ui.states.RegistrationFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
    private val getUserUseCase: GetUserByUsernameUseCase,
    fieldValidationUseCases: FieldValidationUseCases,
) : ViewModel(), UiEventListener<RegistrationFormEvent> {

    private val validateName: ValidateName = fieldValidationUseCases.validateName
    private val validateUsername: ValidateUsername = fieldValidationUseCases.validateUsername
    private val validatePhoneNumber: ValidatePhoneNumber =
        fieldValidationUseCases.validatePhoneNumber
    private val validatePassword: ValidatePassword = fieldValidationUseCases.validatePassword
    private val validateRepeatedPassword: ValidateRepeatedPassword =
        fieldValidationUseCases.validateRepeatedPassword

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

        } else {
            // Register User
            viewModelScope.launch {
                registerUser()
            }
        }
    }

    override fun emitValidationEvent(event: ValidationEvent) {
        _validationEvent.value = ValidationEvent.Idle
        _validationEvent.value = event
    }

    private suspend fun registerUser() {
        val user = User(
            username = state.value.username,
            name = state.value.name,
            phoneNumber = state.value.phoneNumber,
            password = state.value.password,
        )

        // Validate the user before registration
        if (validateUser(user)) {
            try {
                authenticationUseCases.registerUseCase.execute(user)
                saveUserInGlobalState(user)
                emitValidationEvent(ValidationEvent.Success)
            } catch (e: Exception) {
                emitValidationEvent(ValidationEvent.Failed)
            }
        }
    }

    private suspend fun validateUser(user: User): Boolean {
        // Find out if the user already exists
        if (getUserUseCase.execute(user.username) != null) {
            emitValidationEvent(
                ValidationEvent.Failed
                    .setReason("Sorry the username already exist with the corresponding value.")
            )

            return false
        }

        return true
    }

    private fun saveUserInGlobalState(user: User) {
        AppGlobalState.setUser(user)
    }
}