package com.sterdevs.mybills.features.authentication.domain.repository

import com.sterdevs.mybills.core.domain.models.User

interface AuthRepository {
    suspend fun authenticate(username: String, password: String): User?
    suspend fun register(
        name: String,
        username: String,
        phoneNumber: String,
        password: String
    ): User
}