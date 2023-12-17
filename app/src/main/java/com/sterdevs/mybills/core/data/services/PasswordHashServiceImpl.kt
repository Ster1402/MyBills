package com.sterdevs.mybills.core.data.services

import com.sterdevs.mybills.core.domain.services.PasswordHashService

class PasswordHashServiceImpl : PasswordHashService {
    /**
     * Take a raw password in plain text, and compare it
     * with a hashed password.
     *
     * @param [rawPassword] The raw password in plain text that we want to check
     * @param [hashedPassword] The hashed password
     * @return true if the password matches, false if not
     */
    override fun checkPassword(rawPassword: String, hashedPassword: String): Boolean {
        // TODO: Implemented the logic
        return rawPassword == hashedPassword
    }

    /**
     * Help to hash a password before storing
     *
     */
    override fun hashPassword(rawPassword: String): String {
        // TODO("Not yet implemented")
        return rawPassword
    }

}