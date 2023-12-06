package com.sterdevs.mybills.core.domain.use_cases

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository

class GetUserByUsernameUseCase(
    private val _userRepository: UserRepository
) {
    suspend fun execute(username: String): User? = _userRepository.getUserByUsername(username)
}