package com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.errors

import com.sterdevs.mybills.features.authentication.domain.utils.MIN_PASSWORD_LENGTH

class PasswordValidationError(errorMessage: String, description: String) :
    ValidationError(errorMessage, description) {
    companion object {
        val InvalidPassword = PasswordValidationError(
            "The password is not valid.",
            "The password doesn't match."
        )
        val InvalidPasswordFormat = PasswordValidationError(
            "The password format is no valid.",
            "The password should be at least $MIN_PASSWORD_LENGTH characters long."
        )
    }
}