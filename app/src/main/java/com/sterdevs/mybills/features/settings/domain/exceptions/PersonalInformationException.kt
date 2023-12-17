package com.sterdevs.mybills.features.settings.domain.exceptions

class PersonalInformationException(val errorCode: String, val description: String) :
    Exception(description) {
    companion object {
        val UsernameAlreadyUsedError = PersonalInformationException(
            errorCode = "UsernameAlreadyUsed",
            description = "The username you are trying to used belongs to another account."
        )

        val InvalidOldPassword = PersonalInformationException(
            errorCode = "InvalidOldPassword",
            description = "The old password specified isn't correct.\nPlease if you want to change the password, enter the correct one."
        )
    }
}