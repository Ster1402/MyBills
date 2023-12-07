package com.sterdevs.mybills.features.authentication.di

import com.sterdevs.mybills.core.domain.repository.UserRepository
import com.sterdevs.mybills.features.authentication.domain.use_cases.AuthenticationUseCases
import com.sterdevs.mybills.features.authentication.domain.use_cases.LoginUseCase
import com.sterdevs.mybills.features.authentication.domain.use_cases.LogoutUseCase
import com.sterdevs.mybills.features.authentication.domain.use_cases.RegisterUseCase
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.FieldValidationUseCases
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateName
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidatePhoneNumber
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateRepeatedPassword
import com.sterdevs.mybills.features.authentication.domain.use_cases.validation.ValidateUsername
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AuthenticationModule {

    @Provides
    @ViewModelScoped
    fun providesAuthenticationUseCases(userRepository: UserRepository) : AuthenticationUseCases {
        return AuthenticationUseCases(
            loginUseCase = LoginUseCase(userRepository),
            registerUseCase = RegisterUseCase(userRepository),
            logoutUseCase = LogoutUseCase(userRepository)
        )
    }

    @Provides
    @ViewModelScoped
    fun providesFieldValidationUseCases() : FieldValidationUseCases {
        return FieldValidationUseCases(
            validateName = ValidateName(),
            validateUsername = ValidateUsername(),
            validatePhoneNumber = ValidatePhoneNumber(),
            validatePassword = ValidatePassword(),
            validateRepeatedPassword = ValidateRepeatedPassword()
        )
    }

}