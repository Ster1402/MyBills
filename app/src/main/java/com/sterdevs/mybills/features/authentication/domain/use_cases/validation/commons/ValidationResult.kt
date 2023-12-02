package com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons

import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.errors.ValidationError

data class ValidationResult(
    val isSuccessful: Boolean = true,
    val error: ValidationError? = null
)
