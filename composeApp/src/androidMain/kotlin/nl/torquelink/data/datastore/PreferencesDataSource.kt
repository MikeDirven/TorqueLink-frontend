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
import nl.torquelink.shared.models.auth.AuthenticationResponses
import java.io.IOException

class PreferencesDataSource(
    private val dataStore: DataStore<Preferences>
)  {
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

    suspend fun saveTokenInfo(tokenInformation: AuthenticationResponses) {
        Log.d(TAG, "Saving token info")
        when(tokenInformation){
            is AuthenticationResponses.AuthenticationResponseWithRemember -> dataStore.edit { preferences ->
                preferences[SESSION_ACCESS_TOKEN] = tokenInformation.accessToken
                preferences[SESSION_REFRESH_TOKEN] = tokenInformation.refreshToken
                preferences[REMEMBER_TOKEN] = tokenInformation.rememberToken
            }
            else -> dataStore.edit { preferences ->
                preferences[SESSION_ACCESS_TOKEN] = tokenInformation.accessToken
                preferences[SESSION_REFRESH_TOKEN] = tokenInformation.refreshToken
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

    companion object {
        private const val TAG = "PreferencesDataSource"
        val DEVICE_SERIAL = stringPreferencesKey("device_serial")
        val SESSION_ACCESS_TOKEN = stringPreferencesKey("session_token")
        val SESSION_REFRESH_TOKEN = stringPreferencesKey("session_refresh_token")
        val REMEMBER_TOKEN = stringPreferencesKey("remember_token")
    }
}