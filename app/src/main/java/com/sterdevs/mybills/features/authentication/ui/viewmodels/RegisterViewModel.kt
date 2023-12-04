package com.sterdevs.mybills.features.authentication.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePhoneNumber
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateRepeatedPassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateUsername
import com.sterdevs.mybills.features.authentication.ui.events.RegistrationFormEvent
import com.sterdevs.mybills.features.authentication.ui.viewmodels.states.RegistrationFormState

class RegisterViewModel(
    private val validateUsername: ValidateUsername = ValidateUsername(),
    private val validatePhoneNumber: ValidatePhoneNumber = ValidatePhoneNumber(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword()
) : ViewModel() {

    private val _state = MutableLiveData(
        RegistrationFormState()
    )
    val state: LiveData<RegistrationFormState>
        get() = _state

    // Manage an event that occur in the RegistrationForm
    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.UsernameChanged -> {
                _state.value = _state.value?.copy(username = event.username)
            }

            is RegistrationFormEvent.PhoneNumberChanged -> {
                _state.value = _state.value?.copy(phoneNumber = event.phoneNumber)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                _state.value = _state.value?.copy(password = event.password)
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                _state.value = _state.value?.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val usernameValidationResult = validateUsername.execute(state.value!!.username)
    }
}