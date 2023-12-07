package com.sterdevs.mybills.features.news.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.sterdevs.mybills.core.ui.events.validations.ValidationEvent
import com.sterdevs.mybills.core.ui.utils.UiEventListener
import com.sterdevs.mybills.features.news.ui.events.NewsEvent


class NewsViewModel : ViewModel(), UiEventListener<NewsEvent> {

    override fun onEvent(event: NewsEvent) {
        TODO("Not yet implemented")
    }

    override fun emitValidationEvent(event: ValidationEvent) {
        TODO("Not yet implemented")
    }

}