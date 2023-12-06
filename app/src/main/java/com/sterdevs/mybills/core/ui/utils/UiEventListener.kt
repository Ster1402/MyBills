package com.sterdevs.mybills.core.ui.utils

import com.sterdevs.mybills.core.domain.models.validations.ValidationEvent

interface UiEventListener<EventType> {
    fun onEvent(event: EventType)
    fun emitValidationEvent(event: ValidationEvent)
}