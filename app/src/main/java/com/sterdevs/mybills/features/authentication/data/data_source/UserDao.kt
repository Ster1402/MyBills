package com.sterdevs.mybills.features.authentication.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sterdevs.mybills.core.domain.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getUsers() : Flow<List<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUserById(id: Long): User?

    @Query("SELECT * FROM User WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Insert(entity = User::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}