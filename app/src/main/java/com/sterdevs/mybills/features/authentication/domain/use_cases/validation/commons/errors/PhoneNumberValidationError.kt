package com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.errors

class PhoneNumberValidationError(errorMessage: String, description: String) :
    ValidationError(errorMessage, description) {
    companion object {
        val InvalidFormat = PhoneNumberValidationError(
            errorMessage = "The phone number is not valid.",
            description = "Please enter a phone number with a correct format."
        )
    }
}