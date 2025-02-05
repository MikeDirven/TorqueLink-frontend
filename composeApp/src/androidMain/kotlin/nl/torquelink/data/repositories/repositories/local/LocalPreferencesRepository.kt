package nl.torquelink.data.repositories.repositories.local

import nl.torquelink.data.datastore.PreferencesDataSource
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.shared.models.auth.AuthenticationResponses

class LocalPreferencesRepository(
    private val preferencesDataSource: PreferencesDataSource
) : PreferencesRepository {
    override suspend fun getAccessToken(): String? {
        return preferencesDataSource.getSessionAccessToken()
    }

    override suspend fun getRefreshToken(): String? {
        return preferencesDataSource.getSessionRefreshToken()
    }

    override suspend fun getRememberToken(): String? {
        return preferencesDataSource.getRememberToken()
    }

    override suspend fun saveTokenInformation(
        tokenInformation: AuthenticationResponses
    ) {
        return preferencesDataSource.saveTokenInfo(tokenInformation)
    }

    override suspend fun clearTokenInformation(removeRemember: Boolean) {
        preferencesDataSource.clearTokenInfo()
    }
}