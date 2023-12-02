package com.sterdevs.mybills.features.bills.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Picture(
    @PrimaryKey val id : Int? = null,
    val uri: String,

    // Relationships
    val billId: Int? = null
)
