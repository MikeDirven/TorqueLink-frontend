package nl.torquelink.domain.repositories

import nl.torquelink.data.network.result.EmptyResult
import nl.torquelink.data.network.result.Result
import nl.torquelink.shared.models.auth.AuthenticationResponses

interface AuthenticationRepository {
    suspend fun loginByUsername(username: String, password: String): Result<AuthenticationResponses>
    suspend fun loginByEmail(email: String, password: String): Result<AuthenticationResponses>
    suspend fun loginByRememberToken(token: String): Result<AuthenticationResponses>

    suspend fun register(username: String, password: String, email: String): EmptyResult

    suspend fun requestPasswordReset(username: String, email: String) : EmptyResult
    suspend fun resetPassword(token: String, password: String) : EmptyResult

    suspend fun setNotificationToken(notificationToken: String) : EmptyResult
}