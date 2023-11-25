package com.sterdevs.mybills.commons.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id : Int? = null,

    val username : String,
    val name : String,
    val surname : String,
    val countryCode : Int,
    val phoneNumber: Int,
    val avatar: String, // User profile image path
    val password : String,

    val createdAt : Long, // Timestamp
    val updatedAt : Long // Timestamp
)
