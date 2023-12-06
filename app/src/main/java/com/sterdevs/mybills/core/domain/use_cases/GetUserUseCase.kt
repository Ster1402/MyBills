package com.sterdevs.mybills.core.domain.use_cases

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository

class GetUserUseCase(
    private val _userRepository: UserRepository
) {
    suspend fun execute(id: Long) : User? {
        return _userRepository.getUserById(id)
    }
}