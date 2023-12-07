package com.sterdevs.mybills.features.authentication.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.ui.events.validations.ValidationEvent
import com.sterdevs.mybills.core.ui.states.AppGlobalState
import com.sterdevs.mybills.core.ui.utils.UiEventListener
import com.sterdevs.mybills.features.authentication.domain.use_cases.AuthenticationUseCases
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.FieldValidationUseCases
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateUsername
import com.sterdevs.mybills.features.authentication.ui.events.LoginFormEvent
import com.sterdevs.mybills.features.authentication.ui.states.LoginFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
    fieldValidationUseCases: FieldValidationUseCases,
) : ViewModel(), UiEventListener<LoginFormEvent> {
    private val validateUsername: ValidateUsername = fieldValidationUseCases.validateUsername
    private val validatePassword: ValidatePassword = fieldValidationUseCases.validatePassword

    private val _state = MutableStateFlow(LoginFormState())
    val state = _state.asStateFlow()

    private val _validationEvent = MutableStateFlow<ValidationEvent?>(null)
    val validationEvent = _validationEvent.asStateFlow()

    override fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.UsernameChanged -> {
                _state.value = _state.value.copy(
                    username = event.username,
                    usernameError = validateUsername.execute(event.username).error?.errorMessage
                )
            }

            is LoginFormEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    passwordError = validatePassword.execute(event.password).error?.errorMessage
                )
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    override fun emitValidationEvent(event: ValidationEvent) {
        _validationEvent.value = ValidationEvent.Idle
        _validationEvent.value = event
    }

    private fun submitData() {
        val validateUsernameResult = validateUsername.execute(state.value.username)
        val validatePasswordResult = validatePassword.execute(state.value.password)

        val hasError = listOf(
            validateUsernameResult,
            validatePasswordResult
        ).any { !it.isSuccessful }

        if (hasError) {
            // Showing the error in the state
            _state.value = _state.value.copy(
                username = _state.value.username,
                password = _state.value.password,
                usernameError = validateUsername.execute(_state.value.username).error?.errorMessage,
                passwordError = validateUsername.execute(_state.value.password).error?.errorMessage
            )

            return
        }


        // Emit the validation result event for the fragment to observe
        viewModelScope.launch {
            // Try to login
            try {
                val user = login()
                if (user == null) {
                    emitValidationEvent(
                        ValidationEvent.Failed.setReason("Oops! Invalid credentials.")
                    )
                } else {
                    AppGlobalState.setUser(user)
                    emitValidationEvent(
                        ValidationEvent.Success
                    )
                }
            } catch(e: Exception) {
                emitValidationEvent(
                    ValidationEvent.Failed.setReason(
                        "Sorry, an unknown error occur 😢."
                    )
                )
            }
        }
    }

    private suspend fun login() : User? {
        return authenticationUseCases.loginUseCase.execute(
            username = _state.value.username,
            password = _state.value.password,
        )
    }
}