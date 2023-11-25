package com.sterdevs.mybills.commons.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Role(
    @PrimaryKey val id : Int? = null,

    val name : String,
    val userAlias : String,

    // Relationships
    val userId : Int,
    val homeId : Int,

    val createdAt : Long, // Timestamp
    val updatedAt : Long // Timestamp
)
