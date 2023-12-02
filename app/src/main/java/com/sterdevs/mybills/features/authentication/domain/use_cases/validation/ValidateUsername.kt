package com.sterdevs.mybills.features.authentication.domain.use_cases.validation

import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.ValidationResult
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.errors.UsernameValidationError
import com.sterdevs.mybills.features.authentication.domain.utils.MIN_USERNAME_LENGTH

class ValidateUsername {
    fun execute(username: String): ValidationResult {

        // Check if the username is blank
        if (username.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                error = UsernameValidationError.UndefinedValue
            )
        }

        // Check the length of the username is valid
        if (username.length < MIN_USERNAME_LENGTH) {
            return ValidationResult(
                isSuccessful = false,
                error = UsernameValidationError.InvalidUsername
            )
        }

        // Check if there is at least one letter in the username
        if (!username.any { it.isLetter() }) {
            return ValidationResult(
                isSuccessful = false,
                error = UsernameValidationError.InvalidUsername
            )
        }

        return ValidationResult(isSuccessful = true)
    }
}