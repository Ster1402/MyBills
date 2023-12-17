package com.sterdevs.mybills.features.authentication.domain.use_cases

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository
import com.sterdevs.mybills.core.domain.services.PasswordHashService

class LoginUseCase(
    private val _userRepository: UserRepository,
    private val _passwordHashService: PasswordHashService
) {
    suspend fun execute(
        username: String,
        password: String,
    ) : User? {
        val user = _userRepository.getUserByUsername(username) ?: return null

        // Compare encrypted password with the raw password
        if (!_passwordHashService.checkPassword(password, user.password)) {
            return null
        }

        return user
    }
}