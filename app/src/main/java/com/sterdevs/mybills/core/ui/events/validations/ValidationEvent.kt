package com.sterdevs.mybills.core.ui.events.validations

sealed class ValidationEvent {
    data object Pending : ValidationEvent()
    data object Success : ValidationEvent()
    data object Failed : ValidationEvent() {
        private var _reason: String = "An error occur!"
        val reason: String
            get() = _reason

        fun setReason(reason: String): ValidationEvent {
            _reason = reason
            return this
        }
    }
}
