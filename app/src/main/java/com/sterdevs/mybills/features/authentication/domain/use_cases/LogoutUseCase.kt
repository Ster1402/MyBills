package com.sterdevs.mybills.features.authentication.domain.use_cases

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository
import com.sterdevs.mybills.core.ui.state.AppGlobalState
import com.sterdevs.mybills.core.ui.state.AppStateObserver

class LogoutUseCase(
    private val _userRepository: UserRepository
) {
    suspend fun execute(
        user: User,
        observer: AppStateObserver? = null) {
        // Delete user from the database
        _userRepository.deleteUser(user)
        // If an observer is set, then also called the observer method for handling a deletion of user
        if (observer is AppStateObserver) {
            observer.onUserLoggedOut()
        }
    }
}