package com.sterdevs.mybills.features.authentication.domain.use_cases

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository
import com.sterdevs.mybills.core.domain.services.PasswordHashService
import com.sterdevs.mybills.core.ui.states.AppGlobalState
import com.sterdevs.mybills.features.settings.domain.exceptions.PersonalInformationException

class RegisterUseCase(
    private val _userRepository: UserRepository,
    private val _passwordHashService: PasswordHashService
) {
    suspend fun execute(user: User) {
        val userData = user.copy(
            password = _passwordHashService.hashPassword(user.password)
        )

        _userRepository.saveUser(userData)
    }
}