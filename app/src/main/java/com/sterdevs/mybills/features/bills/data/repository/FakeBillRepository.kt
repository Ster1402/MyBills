package com.sterdevs.mybills.features.bills.data.repository

import com.sterdevs.mybills.core.domain.models.enums.BillsTags
import com.sterdevs.mybills.features.bills.domain.models.Bill
import java.time.Month

class FakeBillRepository {
    fun getBills(): List<Bill> {

        return listOf(
            Bill(
                tag = BillsTags.ELECTRICITY.value,
                cost = 5000.0,
                forPeriod = Month.NOVEMBER.name
            ),
            Bill(
                tag = BillsTags.WATER.value,
                unpaidAmount = 2000.0,
                cost = 15000.0,
                forPeriod = Month.DECEMBER.name
            ),
            Bill(
                tag = BillsTags.RENT.value,
                cost = 25000.0,
                forPeriod = Month.DECEMBER.name
            ),
        )
    }
}