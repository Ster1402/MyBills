package com.sterdevs.mybills.core.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sterdevs.mybills.core.domain.models.enums.RoleName
import java.time.Instant

@Entity
data class Role(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    val name : String = RoleName.TENANT.value, // By default it's for tenant
    val userAlias : String,

    // Relationships
    val userId : Int,
    val homeId : Int,

    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val createdAt: Long = Instant.now().toEpochMilli(),
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updatedAt: Long = Instant.now().toEpochMilli(),
)
