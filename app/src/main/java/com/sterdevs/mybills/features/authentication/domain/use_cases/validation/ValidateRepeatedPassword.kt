package com.sterdevs.mybills.features.authentication.domain.use_cases.validation

import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.ValidationResult
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.errors.RepeatedPasswordError

class ValidateRepeatedPassword {

    fun execute(password: String, repeatedPassword: String) : ValidationResult {

        if (password != repeatedPassword) {
            return ValidationResult(
                isSuccessful = false,
                error = RepeatedPasswordError.InvalidRepeatedPassword
            )
        }

        return ValidationResult(isSuccessful = true)
    }
}