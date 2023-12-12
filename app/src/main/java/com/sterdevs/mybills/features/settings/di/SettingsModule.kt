package com.sterdevs.mybills.features.settings.di

import com.sterdevs.mybills.features.settings.domain.use_cases.SettingsUseCases
import com.sterdevs.mybills.features.settings.domain.use_cases.UpdateUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SettingsModule {

    @Provides
    @ViewModelScoped
    fun providesSettingsUseCases() : SettingsUseCases{
        return SettingsUseCases(
            updateUserUseCase = UpdateUserUseCase()
        )
    }
}