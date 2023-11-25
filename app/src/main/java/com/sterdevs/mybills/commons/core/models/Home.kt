package com.sterdevs.mybills.commons.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Home(
    @PrimaryKey val id : Int? = null,

    val name : String,
    val slug : String,
    val description: String,

    // Relationships
    val caretakerId : Int
)