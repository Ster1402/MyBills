package com.sterdevs.mybills.features.authentication.domain.use_cases.validation

import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.ValidationResult
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.errors.NameValidationError
import com.sterdevs.mybills.features.authentication.domain.utils.MAX_NAME_LENGTH
import com.sterdevs.mybills.features.authentication.domain.utils.MIN_NAME_LENGTH

class ValidateName {
    fun execute(name: String): ValidationResult {
        if (name.length !in MIN_NAME_LENGTH..MAX_NAME_LENGTH) {
            return ValidationResult(
                isSuccessful = false,
                error = NameValidationError.InvalidName
            )
        }

        val regex = Regex("^[a-zA-Z\\s\\-]{2,}$")

        if (!regex.matches(name)) {
            return ValidationResult(
                isSuccessful = false,
                error = NameValidationError.InvalidFormat
            )
        }

        return ValidationResult(isSuccessful = true)
    }
}