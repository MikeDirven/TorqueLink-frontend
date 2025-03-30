package nl.torquelink.data.repositories.repositories.remote

import nl.torquelink.data.datastore.PreferencesDataSource
import nl.torquelink.data.network.TorqueLinkApi
import nl.torquelink.data.network.result.EmptyResult
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.Result
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.domain.repositories.GroupsRepository
import nl.torquelink.domain.repositories.UsersRepository
import nl.torquelink.shared.filters.Filters
import nl.torquelink.shared.models.Pageable
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.auth.LoginRequests
import nl.torquelink.shared.models.auth.RegistrationRequests
import nl.torquelink.shared.models.auth.ResetPasswordRequests
import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.models.profile.UserProfiles

class RemoteGroupsRepository(
    private val preferencesDataSource: PreferencesDataSource,
    private val torqueLinkApi: TorqueLinkApi
) : GroupsRepository {
    override suspend fun getGroups(
        page: Int,
        limit: Int,
        filters: Filters?
    ): Result<Pageable<Groups.GroupDto>> {
        val accessToken = preferencesDataSource.getSessionAccessToken()
            ?: return ErrorResult.Error(Exception("No access token found"))

        return try {
            torqueLinkApi.groupsApi.getGroups(
                accessToken,
                page,
                limit,
                filters
            )
        } catch(e: Exception) {
            return ErrorResult.Error(e)
        }
    }
}