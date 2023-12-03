package com.sterdevs.mybills.core.domain.models.viewmodels

import com.sterdevs.mybills.core.domain.models.validations.ValidationEvent

interface IViewModels<EventType> {
    fun onEvent(event: EventType)
    fun emitValidationEvent(event: ValidationEvent)
}