package com.sterdevs.mybills.core.ui.states

interface AppGlobalStateObserver {
    fun onUserAuthenticate() {}
    fun onUserLoggedOut() {
        AppGlobalState.clearUserState()
    }
    suspend fun observeUserStateChanged()
}
