package com.sterdevs.mybills.core.domain.repository

import com.sterdevs.mybills.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<List<User>>

    suspend fun getUserById(id : Long) : User?
    suspend fun getUserByUsername(username: String) : User?
    suspend fun saveUser(user: User)
    suspend fun deleteUser(user: User)
}