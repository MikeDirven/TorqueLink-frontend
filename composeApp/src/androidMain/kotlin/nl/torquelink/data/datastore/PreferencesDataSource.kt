package nl.torquelink.data.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.profile.UserProfiles
import java.io.IOException

class PreferencesDataSource(
    private val dataStore: DataStore<Preferences>
)  {
    private val serializer: Json = Json

    private val sessionAccessTokenFlow: Flow<String?> = dataStore.data.catch {
        if (it is IOException) {
            Log.e(TAG, "Error reading access token", it)
            emit(emptyPreferences())
        } else throw it
    }.map { preferences ->
        preferences[SESSION_ACCESS_TOKEN]?.ifBlank { null }
    }

    private val sessionRefreshTokenFlow: Flow<String?> = dataStore.data.catch {
        if (it is IOException) {
            Log.e(TAG, "Error reading access token", it)
            emit(emptyPreferences())
        } else throw it
    }.map { preferences ->
        preferences[SESSION_REFRESH_TOKEN]?.ifBlank { null }
    }

    private val profileFlow: Flow<UserProfiles.UserProfileWithSettingsDto?> = dataStore.data.catch {
        if (it is IOException) {
            Log.e(TAG, "Error reading profile", it)
            emit(emptyPreferences())
        } else throw it
    }.map { preferences ->
        preferences[PROFILE]?.let {
            serializer.decodeFromString(UserProfiles.UserProfileWithSettingsDto.serializer(), it)
        }
    }

    suspend fun getSessionAccessToken(): String? {
        return try {
            dataStore.data.first()[SESSION_ACCESS_TOKEN]?.ifBlank { null }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getSessionRefreshToken(): String? {
        return try {
            dataStore.data.first()[SESSION_REFRESH_TOKEN]?.ifBlank { null }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getRememberToken(): String? {
        return try {
            dataStore.data.first()[REMEMBER_TOKEN]?.ifBlank { null }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getProfile() : UserProfiles.UserProfileWithSettingsDto? {
        return try {
            dataStore.data.first()[PROFILE]?.let {
                serializer.decodeFromString(UserProfiles.UserProfileWithSettingsDto.serializer(), it)
            }
        } catch (e: IOException) {
            null
        }
    }

    suspend fun saveTokenInfo(tokenInformation: AuthenticationResponses) {
        Log.d(TAG, "Saving token info")
        when(tokenInformation){
            is AuthenticationResponses.AuthenticationResponseWithRemember -> {
                dataStore.edit { preferences ->
                    preferences[SESSION_ACCESS_TOKEN] = tokenInformation.accessToken
                    preferences[SESSION_REFRESH_TOKEN] = tokenInformation.refreshToken
                    preferences[REMEMBER_TOKEN] = tokenInformation.rememberToken
                }
            }
            is AuthenticationResponses.AuthenticationResponseWithRememberAndProfile -> {
                dataStore.edit { preferences ->
                    preferences[SESSION_ACCESS_TOKEN] = tokenInformation.accessToken
                    preferences[SESSION_REFRESH_TOKEN] = tokenInformation.refreshToken
                    preferences[REMEMBER_TOKEN] = tokenInformation.rememberToken
                    preferences[PROFILE] = serializer.encodeToString(tokenInformation.profile)
                }
            }
            else -> {
                dataStore.edit { preferences ->
                    preferences[SESSION_ACCESS_TOKEN] = tokenInformation.accessToken
                    preferences[SESSION_REFRESH_TOKEN] = tokenInformation.refreshToken
                }
            }
        }
    }

    suspend fun clearTokenInfo(removeRememberToken: Boolean = false) {
        Log.d(TAG, "Clearing token info started")
        dataStore.edit {
            it[SESSION_ACCESS_TOKEN] = ""
            it[SESSION_REFRESH_TOKEN] = ""
            if(removeRememberToken) it[REMEMBER_TOKEN] = ""
        }
        Log.d(TAG, "Clearing token info finished")
    }

    suspend fun setNotificationToken(token: String) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATION_TOKEN] = token
        }
    }

    suspend fun getNotificationToken(): String? {
        return try {
            dataStore.data.first()[NOTIFICATION_TOKEN]?.ifBlank { null }
        } catch (e: IOException) {
            null
        }
    }

    companion object {
        private const val TAG = "PreferencesDataSource"
        val PROFILE = stringPreferencesKey("profile")
        val SESSION_ACCESS_TOKEN = stringPreferencesKey("session_token")
        val SESSION_REFRESH_TOKEN = stringPreferencesKey("session_refresh_token")
        val REMEMBER_TOKEN = stringPreferencesKey("remember_token")
        val NOTIFICATION_TOKEN = stringPreferencesKey("notification_token")
    }
}