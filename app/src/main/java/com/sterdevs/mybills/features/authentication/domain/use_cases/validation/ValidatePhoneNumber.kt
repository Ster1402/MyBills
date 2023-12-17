package com.sterdevs.mybills.features.authentication.domain.use_cases.validation

import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.ValidationResult
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.errors.PhoneNumberValidationError

class ValidatePhoneNumber {
    fun execute(phoneNumber: String): ValidationResult {
        val regex = Regex("6[25987][0-9]{7}")

        if (!regex.matches(phoneNumber)) {
            return ValidationResult(
                isSuccessful = false,
                error = PhoneNumberValidationError.InvalidFormat
            )
        }

        return ValidationResult(isSuccessful = true)
    }
}