package com.sterdevs.mybills.core.domain.models.ui

interface ScreenUtils {
    fun getViews()
    fun initializeDefaultValues()
    fun addViewsEventsListeners()
    fun addLiveDataObservers() {}
}