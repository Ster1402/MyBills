package com.sterdevs.mybills.core.ui.states

interface AppGlobalStateObserver {
    fun onUserAuthenticate() {}
    fun onUserLoggedOut() {}
}
