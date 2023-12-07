package com.sterdevs.mybills.features.authentication.domain.use_cases

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository

class RegisterUseCase(
    private val _userRepository: UserRepository
) {
    suspend fun execute(user: User) {
        _userRepository.saveUser(user)
    }
}