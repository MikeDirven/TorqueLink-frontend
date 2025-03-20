package nl.torquelink.data.repositories.repositories.remote

import nl.torquelink.data.datastore.PreferencesDataSource
import nl.torquelink.data.network.TorqueLinkApi
import nl.torquelink.data.network.result.EmptyResult
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.Result
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.domain.repositories.UsersRepository
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.auth.LoginRequests
import nl.torquelink.shared.models.auth.RegistrationRequests
import nl.torquelink.shared.models.auth.ResetPasswordRequests
import nl.torquelink.shared.models.profile.UserProfiles

class RemoteUsersRepository(
    private val preferencesDataSource: PreferencesDataSource,
    private val torqueLinkApi: TorqueLinkApi
) : UsersRepository {
    override suspend fun createUserProfile(
        request: UserProfiles.UserProfileCreateDto
    ): Result<UserProfiles.UserProfileDto> {
        val accessToken = preferencesDataSource.getSessionAccessToken()
            ?: return ErrorResult.Error(Exception("No access token found"))

        return try {
            torqueLinkApi.usersApi.createUserProfile(
                accessToken,
                request
            )
        } catch(e: Exception) {
            return ErrorResult.Error(e)
        }
    }
}