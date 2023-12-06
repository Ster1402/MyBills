package com.sterdevs.mybills.core.ui.state

interface AppStateObserver {
    fun onUserAuthenticate()
    fun onUserLoggedOut()
}
