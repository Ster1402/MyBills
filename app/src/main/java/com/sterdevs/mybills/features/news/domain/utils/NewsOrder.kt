package com.sterdevs.mybills.features.news.domain.utils

import com.sterdevs.mybills.core.domain.utils.OrderType

sealed class NewsOrder(val orderType: OrderType) {
    class Date(orderType: OrderType) : NewsOrder(orderType)
    class Alphabetic(orderType: OrderType) : NewsOrder(orderType)
}