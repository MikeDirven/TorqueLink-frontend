package nl.torquelink.data.repositories.local

import nl.torquelink.data.datastore.PreferencesDataSource
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.profile.UserProfiles
import org.w3c.dom.Storage

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

    override suspend fun getProfile(): UserProfiles.UserProfileWithSettingsDto? {
        return preferencesDataSource.getProfile()
    }

    override suspend fun saveTokenInformation(
        tokenInformation: AuthenticationResponses
    ) {
        return preferencesDataSource.saveTokenInfo(tokenInformation)
    }

    override suspend fun clearTokenInformation(removeRemember: Boolean) {
        preferencesDataSource.clearTokenInfo()
    }

    override suspend fun getNotificationToken(): String? {
        return preferencesDataSource.getNotificationToken()
    }

    override suspend fun setNotificationToken(token: String) {
        return preferencesDataSource.setNotificationToken(token)
    }
}