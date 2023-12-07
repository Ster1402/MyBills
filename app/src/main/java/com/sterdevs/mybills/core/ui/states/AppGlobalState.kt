package com.sterdevs.mybills.core.ui.states

import com.sterdevs.mybills.core.domain.models.User
import kotlinx.coroutines.flow.MutableStateFlow

object AppGlobalState {
    private val _userState = MutableStateFlow<User?>(null)
    val userState = _userState

    fun clearUserState() {
        _userState.value = null
    }

    fun setUser(user: User) {
        _userState.value = user
    }
}