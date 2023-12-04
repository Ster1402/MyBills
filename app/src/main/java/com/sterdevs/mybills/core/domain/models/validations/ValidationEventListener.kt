package com.sterdevs.mybills.core.domain.models.validations

interface ValidationEventListener {
    fun handleValidationEvent(event: ValidationEvent?)
}