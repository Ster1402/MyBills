package com.sterdevs.mybills.features.authentication.domain.use_cases.validation.commons.errors

class RepeatedPasswordError(errorMessage: String, description: String) :
    ValidationError(errorMessage, description) {
    companion object {
        val InvalidRepeatedPassword = RepeatedPasswordError(
            errorMessage = "The repeated password is not correct.",
            description = "The repeated password need to match the password."
        )
    }
}