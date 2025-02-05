package nl.torquelink.domain.repositories

import nl.torquelink.shared.models.auth.AuthenticationResponses

interface PreferencesRepository {
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun getRememberToken(): String?
    suspend fun saveTokenInformation(tokenInformation: AuthenticationResponses)
    suspend fun clearTokenInformation(removeRemember: Boolean)
}