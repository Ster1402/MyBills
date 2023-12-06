package com.sterdevs.mybills.core.domain.utils

// How to order a list element
sealed class OrderType {
    data object Asc : OrderType()
    data object Desc : OrderType()
}
