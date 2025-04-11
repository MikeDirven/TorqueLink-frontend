package nl.torquelink.data.repositories.remote

import nl.torquelink.data.network.TorqueLinkApi
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.Result
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.domain.repositories.UsersRepository
import nl.torquelink.shared.models.profile.UserProfiles

class RemoteUsersRepository(
    private val preferencesDataSource: PreferencesRepository,
    private val torqueLinkApi: TorqueLinkApi
) : UsersRepository {
    override suspend fun createUserProfile(
        request: UserProfiles.UserProfileCreateDto
    ): Result<UserProfiles.UserProfileDto> {
        val accessToken = preferencesDataSource.getAccessToken()
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