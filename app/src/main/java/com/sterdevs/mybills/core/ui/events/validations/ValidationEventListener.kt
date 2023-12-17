package com.sterdevs.mybills.core.ui.events.validations

interface ValidationEventListener {
    fun handleValidationEvent(event: ValidationEvent?)
}