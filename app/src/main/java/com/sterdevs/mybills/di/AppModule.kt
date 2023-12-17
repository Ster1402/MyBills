package com.sterdevs.mybills.di

import android.app.Application
import androidx.room.Room
import com.sterdevs.mybills.core.data.data_source.MyBillsDatabase
import com.sterdevs.mybills.core.data.repository.UserRepositoryImpl
import com.sterdevs.mybills.core.data.services.PasswordHashServiceImpl
import com.sterdevs.mybills.core.domain.repository.UserRepository
import com.sterdevs.mybills.core.domain.services.PasswordHashService
import com.sterdevs.mybills.core.domain.use_cases.GetUserByUsernameUseCase
import com.sterdevs.mybills.core.domain.use_cases.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesDatabase(app : Application) : MyBillsDatabase {
        return Room.databaseBuilder(
            context = app,
            klass = MyBillsDatabase::class.java,
            name = MyBillsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesPasswordHashService(): PasswordHashService {
        return PasswordHashServiceImpl()
    }

    @Provides
    @Singleton
    fun providesUserRepository(db: MyBillsDatabase) : UserRepository {
        return UserRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun providesGetUserUseCase(userRepository: UserRepository) : GetUserUseCase {
        return GetUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun providesGetUserByUsernameUseCase(userRepository: UserRepository) : GetUserByUsernameUseCase {
        return GetUserByUsernameUseCase(userRepository)
    }
}