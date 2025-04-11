package nl.torquelink.data.repositories.remote

import nl.torquelink.data.network.TorqueLinkApi
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.Result
import nl.torquelink.domain.repositories.GroupsRepository
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.shared.filters.Filters
import nl.torquelink.shared.models.Pageable
import nl.torquelink.shared.models.group.Groups

class RemoteGroupsRepository(
    private val preferencesDataSource: PreferencesRepository,
    private val torqueLinkApi: TorqueLinkApi
) : GroupsRepository {
    override suspend fun getGroups(
        page: Int,
        limit: Int,
        filters: Filters?
    ): Result<Pageable<Groups.GroupDto>> {
        val accessToken = preferencesDataSource.getAccessToken()
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