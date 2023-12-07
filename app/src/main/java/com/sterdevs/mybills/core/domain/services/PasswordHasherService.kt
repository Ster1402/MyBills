package com.sterdevs.mybills.core.domain.services

interface PasswordHashService {
    /**
     * Take a raw password in plain text, and compare it
     * with a hashed password.
     *
     * @param [rawPassword] The raw password in plain text that we want to check
     * @param [hashedPassword] The hashed password
     * @return true if the password matches, false if not
     */
    fun checkPassword(rawPassword: String, hashedPassword: String): Boolean

    /**
     * Help to hash a password before storing
     *
     */
    fun hashPassword(rawPassword: String) : String
}