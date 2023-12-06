package com.sterdevs.mybills.features.authentication.domain.use_cases

import com.sterdevs.mybills.core.domain.repository.UserRepository

class LoginUseCase(
    private val _userRepository: UserRepository
) {
    suspend fun execute() {
        TODO("Execute the logic of logging the user, not yet implemented.")
    }
}