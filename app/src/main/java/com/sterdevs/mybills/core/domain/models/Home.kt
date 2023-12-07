package com.sterdevs.mybills.core.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.Instant

@Entity
data class Home(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    val name : String,
    val slug : String,

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    val description: String = "",

    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = Instant.now().toEpochMilli(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: Long = Instant.now().toEpochMilli(),

    // Relationships
    val caretakerId : Int
)
