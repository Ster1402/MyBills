package com.sterdevs.mybills.features.bills.domain.utils

import com.sterdevs.mybills.core.domain.utils.OrderType

sealed class BillOrder(val orderType: OrderType) {
    class Date(orderType: OrderType) : BillOrder(orderType)
    class Status(orderType: OrderType) : BillOrder(orderType)
    class Category(orderType: OrderType) : BillOrder(orderType) // BillType
}
