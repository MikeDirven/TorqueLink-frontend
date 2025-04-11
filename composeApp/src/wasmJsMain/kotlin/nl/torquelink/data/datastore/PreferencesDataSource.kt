package nl.torquelink.data.datastore

import kotlinx.browser.window
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.io.IOException
import kotlinx.serialization.json.Json
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.profile.UserProfiles
import org.w3c.dom.Storage
import org.w3c.dom.get
import org.w3c.dom.set

class PreferencesDataSource(
    private val storage: Storage
)  {
    private val serializer: Json = Json

    suspend fun getSessionAccessToken(): String? {
        return try {
            storage[SESSION_ACCESS_TOKEN]?.ifBlank { null }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getSessionRefreshToken(): String? {
        return try {
            storage[SESSION_REFRESH_TOKEN]?.ifBlank { null }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getRememberToken(): String? {
        return try {
            storage[REMEMBER_TOKEN]?.ifBlank { null }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getProfile() : UserProfiles.UserProfileWithSettingsDto? {
        return try {
            storage[PROFILE]?.let {
                serializer.decodeFromString(UserProfiles.UserProfileWithSettingsDto.serializer(), it)
            }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun saveTokenInfo(tokenInformation: AuthenticationResponses) {
        println("$TAG, Saving token info")
        when(tokenInformation){
            is AuthenticationResponses.AuthenticationResponseWithRemember -> {
                storage[SESSION_ACCESS_TOKEN] = tokenInformation.accessToken
                storage[SESSION_REFRESH_TOKEN] = tokenInformation.refreshToken
                storage[REMEMBER_TOKEN] = tokenInformation.rememberToken
            }
            is AuthenticationResponses.AuthenticationResponseWithRememberAndProfile -> {
                storage[SESSION_ACCESS_TOKEN] = tokenInformation.accessToken
                storage[SESSION_REFRESH_TOKEN] = tokenInformation.refreshToken
                storage[REMEMBER_TOKEN] = tokenInformation.rememberToken
                storage[PROFILE] = serializer.encodeToString(tokenInformation.profile)
            }
            else -> {
                storage[SESSION_ACCESS_TOKEN] = tokenInformation.accessToken
                storage[SESSION_REFRESH_TOKEN] = tokenInformation.refreshToken
            }
        }
    }

    suspend fun clearTokenInfo(removeRememberToken: Boolean = false) {
        println("$TAG, Clearing token info started")
        storage[SESSION_ACCESS_TOKEN] = ""
        storage[SESSION_REFRESH_TOKEN] = ""
        if(removeRememberToken) storage[REMEMBER_TOKEN] = ""
        println("$TAG, Clearing token info finished")
    }

    suspend fun setNotificationToken(token: String) {
        storage[NOTIFICATION_TOKEN] = token
    }

    suspend fun getNotificationToken(): String? {
        return try {
            storage[NOTIFICATION_TOKEN]?.ifBlank { null }
        } catch (e: IOException) {
            null
        }
    }

    companion object {
        private const val TAG = "PreferencesDataSource"
        val PROFILE = "profile"
        val SESSION_ACCESS_TOKEN = "session_token"
        val SESSION_REFRESH_TOKEN = "session_refresh_token"
        val REMEMBER_TOKEN = "remember_token"
        val NOTIFICATION_TOKEN = "notification_token"
    }
}