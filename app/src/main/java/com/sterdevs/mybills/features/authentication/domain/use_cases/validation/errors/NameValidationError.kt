package com.sterdevs.mybills.features.authentication.domain.use_cases.validation.errors

import com.sterdevs.mybills.features.authentication.domain.utils.MAX_NAME_LENGTH
import com.sterdevs.mybills.features.authentication.domain.utils.MIN_NAME_LENGTH

class NameValidationError(errorMessage: String, description: String) :
    ValidationError(errorMessage, description) {
    companion object {
        val InvalidName = NameValidationError(
            "The user name isn't valid.",
            "The name should be at least $MIN_NAME_LENGTH and at most $MAX_NAME_LENGTH characters long."
        )
        val InvalidFormat = NameValidationError(
            "The name format isn't valid.",
            "The name should contain only letters or hyphen (-)."
        )
    }
}