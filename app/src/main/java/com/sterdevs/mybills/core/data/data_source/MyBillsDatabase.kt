package com.sterdevs.mybills.core.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sterdevs.mybills.core.domain.models.User
import com.sterdevs.mybills.features.authentication.data.data_source.UserDao

@Database(
    entities = [User::class],
    version = 1
)
abstract class MyBillsDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "mybills_db"
    }
}