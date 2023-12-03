package com.sterdevs.mybills.features.authentication.domain.use_cases.validation.errors

import com.sterdevs.mybills.features.authentication.domain.utils.MIN_USERNAME_LENGTH

class UsernameValidationError(errorMessage: String, description: String) :
    ValidationError(errorMessage, description) {
    companion object {
        val UsernameAlreadyUsed = UsernameValidationError(
            "The username is already used.",
            "The username is already used by another account."
        )
        val InvalidUsername = UsernameValidationError(
            "The username is not valid.",
            "The username format is not valid. " +
                    "The username should be at least $MIN_USERNAME_LENGTH characters long, " +
                    "with one letter at least."
        )
        val UndefinedValue = UsernameValidationError(
            "There is no value for the username.",
            "This username doesn't have any value."
        )
    }
}