package nl.torquelink.domain.repositories

import nl.torquelink.data.network.result.Result
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.profile.UserProfiles

interface UsersRepository {
    suspend fun createUserProfile(
        request: UserProfiles.UserProfileCreateDto
    ): Result<UserProfiles.UserProfileDto>
}