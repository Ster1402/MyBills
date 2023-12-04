package com.sterdevs.mybills.features.authentication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sterdevs.mybills.core.domain.models.validations.ValidationEvent
import com.sterdevs.mybills.core.domain.models.viewmodels.IViewModels
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateUsername
import com.sterdevs.mybills.features.authentication.ui.events.LoginFormEvent
import com.sterdevs.mybills.features.authentication.ui.viewmodels.states.LoginFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val validateUsername: ValidateUsername = ValidateUsername(),
    private val validatePassword: ValidatePassword = ValidatePassword()
) : ViewModel(), IViewModels<LoginFormEvent> {

    private val _state = MutableStateFlow(LoginFormState())
    val state = _state.asStateFlow()

    private val _validationEvent = MutableStateFlow<ValidationEvent?>(null)
    val validationEvent = _validationEvent.asStateFlow()

    override fun onEvent(event: LoginFormEvent) {
        when(event) {
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
        }

        // Emit the validation result event for the fragment to observe
        viewModelScope.launch {
            emitValidationEvent(
                if (hasError) {
                    ValidationEvent.Failed
                } else {
                    ValidationEvent.Success
                }
            )
        }
    }
}