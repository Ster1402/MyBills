package com.sterdevs.mybills.core.data.repository

import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.core.domain.repository.UserRepository
import com.sterdevs.mybills.features.authentication.data.data_source.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val _userDao : UserDao
) : UserRepository {
    override fun getUsers(): Flow<List<User>> {
        return _userDao.getUsers()
    }

    override suspend fun getUserById(id: Long): User? {
        return _userDao.getUserById(id)
    }

    override suspend fun getUserByUsername(username: String): User? {
        return _userDao.getUserByUsername(username)
    }

    override suspend fun saveUser(user: User) {
        _userDao.saveUser(user)
    }

    override suspend fun deleteUser(user: User) {
        _userDao.deleteUser(user)
    }
}