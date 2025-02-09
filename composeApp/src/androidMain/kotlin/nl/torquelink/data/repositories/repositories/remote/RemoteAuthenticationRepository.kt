package nl.torquelink.data.repositories.repositories.remote

import nl.torquelink.data.datastore.PreferencesDataSource
import nl.torquelink.data.network.TorqueLinkApi
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
        return torqueLinkApi.authenticationApi.login(
            LoginRequests.UsernameLoginRequest(
                username = username,
                password = password
            )
        )
    }

    override suspend fun loginByEmail(email: String, password: String): Result<AuthenticationResponses> {
        return torqueLinkApi.authenticationApi.login(
            LoginRequests.EmailLoginRequest(
                email = email,
                password = password
            )
        )
    }

    override suspend fun register(username: String, password: String, email: String): Result<Unit> {
        return torqueLinkApi.authenticationApi.register(
            RegistrationRequests.RegisterWithTorqueLinkDto(
                username = username,
                password = password,
                email = email
            )
        )
    }
}