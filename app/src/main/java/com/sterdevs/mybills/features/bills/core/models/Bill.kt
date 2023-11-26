package com.sterdevs.mybills.features.bills.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sterdevs.mybills.commons.core.models.enums.BillsStatus
import com.sterdevs.mybills.features.bills.core.utils.monthInSeconds
import java.time.Instant

@Entity
data class Bill(
    @PrimaryKey val id: Int? = null,

    val tag: String,
    val status: String = BillsStatus.UNPAID.value,
    val unpaidAmount: Double = 0.0,
    val cost: Double,
    val forPeriod: String,

    val createdAt: Long = Instant.now().toEpochMilli(),
    val updatedAt: Long = Instant.now().toEpochMilli(),
    val deadlineAt: Long = Instant.now().plusSeconds(monthInSeconds).toEpochMilli()
)
