package com.sterdevs.mybills.core.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    val username : String,
    val name : String,
    val countryCode : Int = 237,
    val phoneNumber: String,
    val avatar: String = "", // User profile image path
    val password : String,

    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = Instant.now().toEpochMilli(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: Long = Instant.now().toEpochMilli(),
)
