package com.sterdevs.mybills.features.authentication.domain.use_cases.validation

data class FieldValidationUseCases(
    val validateName: ValidateName,
    val validateUsername: ValidateUsername,
    val validatePhoneNumber: ValidatePhoneNumber,
    val validatePassword: ValidatePassword,
    val validateRepeatedPassword: ValidateRepeatedPassword
)
