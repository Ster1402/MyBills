package com.sterdevs.mybills.core.domain.models.validations

sealed class ValidationEvent {
    data object Success : ValidationEvent()
    data object Failed : ValidationEvent()
}