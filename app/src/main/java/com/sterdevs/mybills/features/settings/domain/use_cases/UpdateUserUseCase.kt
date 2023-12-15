package com.sterdevs.mybills.features.settings.domain.use_cases

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository
import com.sterdevs.mybills.core.domain.services.PasswordHashService
import com.sterdevs.mybills.core.ui.states.AppGlobalState
import com.sterdevs.mybills.features.settings.domain.exceptions.PersonalInformationException

class UpdateUserUseCase(
    private val _userRepository: UserRepository,
    private val _passwordHashService: PasswordHashService
) {
    suspend fun execute(user: User, oldPassword: String? = null, newPassword: String? = null) {
        var userData = user

        // 1- Check if the current username is not already used
        _userRepository.getUserByUsername(user.username)?.let {
            if (it.id != AppGlobalState.userState.value?.id) {
                throw PersonalInformationException.UsernameAlreadyUsedError
            }
        }

        newPassword?.let {
            // 2- Check the old password
            if (!_passwordHashService.checkPassword(oldPassword ?: "", user.password)) {
                throw PersonalInformationException.InvalidOldPassword
            }

            val password = _passwordHashService.hashPassword(it)
            userData = user.copy(password = password)
        }

        _userRepository.saveUser(userData)

        AppGlobalState.setUser(userData)
    }
}