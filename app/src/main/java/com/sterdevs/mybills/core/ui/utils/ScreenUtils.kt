package com.sterdevs.mybills.core.ui.utils

interface ScreenUtils {
    fun getViews()
    fun initializeDefaultValues()
    fun addViewsEventsListeners()

    /**
     * Important: Fragments should always use the viewLifecycleOwner to trigger UI updates.
     */
    fun subscribeToObservables() {}
}
