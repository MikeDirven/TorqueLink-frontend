package nl.torquelink.domain.repositories

import nl.torquelink.data.network.result.Result
import nl.torquelink.shared.filters.Filters
import nl.torquelink.shared.models.Pageable
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.models.profile.UserProfiles

interface GroupsRepository {
    suspend fun getGroups(
        page: Int = 1,
        limit: Int = 10,
        filters: Filters? = null
    ): Result<Pageable<Groups.GroupDto>>

    suspend fun getGroupDetails(
        groupId: Long
    ): Result<Groups.GroupWithDetailsDto>
}