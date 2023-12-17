package com.sterdevs.mybills.features.authentication.domain.use_cases.validation

import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.ValidationResult
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.errors.PasswordValidationError
import com.sterdevs.mybills.features.authentication.domain.utils.MIN_PASSWORD_LENGTH

class ValidatePassword {
    fun execute(password: String) : ValidationResult {

        if (password.length < MIN_PASSWORD_LENGTH) {
            return ValidationResult(
                isSuccessful = false,
                error = PasswordValidationError.InvalidPasswordFormat
            )
        }

        return ValidationResult(isSuccessful = true)
    }
}