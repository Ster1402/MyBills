package com.sterdevs.mybills.features.authentication.domain.use_cases

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository

class LoginUseCase(
    private val _userRepository: UserRepository
) {
    suspend fun execute(
        username: String,
        password: String,
    ) : User? {
        val user = _userRepository.getUserByUsername(username)
        // TODO: Compare encrypted password with the raw password
        if (password != user?.password) {
            return null
        }

        return user
    }
}