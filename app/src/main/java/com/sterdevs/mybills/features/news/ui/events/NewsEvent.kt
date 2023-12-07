package com.sterdevs.mybills.features.news.ui.events

sealed class NewsEvent() {
    data class ShowMoreNews(val homeId: Long) : NewsEvent()
}