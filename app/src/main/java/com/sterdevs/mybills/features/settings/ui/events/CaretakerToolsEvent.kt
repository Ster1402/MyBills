package com.sterdevs.mybills.features.settings.ui.events

sealed class CaretakerToolsEvent: SettingsEvent() {
    data object CreateNewHomeFired: CaretakerToolsEvent()
    data object CreateBillFired: CaretakerToolsEvent()
    data object PublishHomeMessageFired: CaretakerToolsEvent()
}