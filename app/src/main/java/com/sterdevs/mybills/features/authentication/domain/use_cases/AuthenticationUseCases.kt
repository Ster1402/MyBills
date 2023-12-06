package com.sterdevs.mybills.features.authentication.domain.use_cases

data class AuthenticationUseCases(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
    val logoutUseCase: LogoutUseCase
)
