package com.sterdevs.mybills.features.news.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)
