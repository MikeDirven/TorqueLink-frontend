package nl.torquelink.data.repositories.repositories.remote

import nl.torquelink.data.datastore.PreferencesDataSource
import nl.torquelink.data.network.TorqueLinkApi
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.Result
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.auth.LoginRequests
import nl.torquelink.shared.models.auth.RegistrationRequests

class RemoteAuthenticationRepository(
    private val preferencesDataSource: PreferencesDataSource,
    private val torqueLinkApi: TorqueLinkApi
) : AuthenticationRepository {
    override suspend fun loginByUsername(username: String, password: String): Result<AuthenticationResponses> {
        return try {
            // Check inputs
            when {
                username.isBlank() -> return ErrorResult.Error(Exception("Username cannot be blank"))
                password.isBlank() -> return ErrorResult.Error(Exception("Password cannot be blank"))
            }
            torqueLinkApi.authenticationApi.login(
                LoginRequests.UsernameLoginRequest(
                    username = username,
                    password = password,
                    remember = true
                )
            )
        } catch(e: Exception) {
            return ErrorResult.Error(e)
        }
    }

    override suspend fun loginByEmail(email: String, password: String): Result<AuthenticationResponses> {
        return try {
            // Check inputs
            when {
                email.isBlank() -> return ErrorResult.Error(Exception("Email cannot be blank"))
                password.isBlank() -> return ErrorResult.Error(Exception("Password cannot be blank"))
            }
            torqueLinkApi.authenticationApi.login(
                LoginRequests.EmailLoginRequest(
                    email = email,
                    password = password
                )
            )
        } catch(e: Exception) {
            return ErrorResult.Error(e)
        }
    }

    override suspend fun loginByRememberToken(token: String): Result<AuthenticationResponses> {
        return try {
            // Check inputs
            when {
                token.isBlank() -> return ErrorResult.Error(Exception("No remember token found for this device!"))
            }
            torqueLinkApi.authenticationApi.login(
                LoginRequests.RememberTokenLoginRequest(
                    rememberToken = token
                )
            )
        } catch(e: Exception) {
            return ErrorResult.Error(e)
        }
    }

    override suspend fun register(username: String, password: String, email: String): Result<Unit> {
        return try {
            // Check inputs
            when {
                username.isBlank() -> return ErrorResult.Error(Exception("Username cannot be blank"))
                password.isBlank() -> return ErrorResult.Error(Exception("Password cannot be blank"))
                email.isBlank() -> return ErrorResult.Error(Exception("Email cannot be blank"))
            }
            torqueLinkApi.authenticationApi.register(
                RegistrationRequests.RegisterWithTorqueLinkDto(
                    username = username,
                    password = password,
                    email = email
                )
            )
        } catch (e: Exception) {
            return ErrorResult.Error(e)
        }
    }
}