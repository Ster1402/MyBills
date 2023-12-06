package com.sterdevs.mybills.features.authentication.data.repository

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository
import com.sterdevs.mybills.features.authentication.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val _userRepository: UserRepository
) : AuthRepository {
    override suspend fun authenticate(username: String, password: String): User? {
        TODO("Find the user in the database")
    }

    override suspend fun register(
        name: String,
        username: String,
        phoneNumber: String,
        password: String
    ): User {
        TODO("Save the user into the database")
    }
}