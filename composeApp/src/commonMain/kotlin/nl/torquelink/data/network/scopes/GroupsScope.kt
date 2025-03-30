package nl.torquelink.data.network.scopes

import io.ktor.client.plugins.resources.get
import io.ktor.client.request.header
import nl.torquelink.data.network.client.httpClient
import nl.torquelink.data.network.result.Result
import nl.torquelink.data.network.result.decode
import nl.torquelink.shared.filters.Filters
import nl.torquelink.shared.models.Pageable
import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.routing.subRouting.TorqueLinkGroupRoutingV1

object GroupsScope {
    suspend fun getGroups(
        token: String,
        page: Int = 1,
        limit: Int = 10,
        filters: Filters? = null
    ) : Result<Pageable<Groups.GroupDto>> {
        return httpClient.get(
            TorqueLinkGroupRoutingV1.Groups(
                page = page,
                limit = limit,
                filters = filters
            )
        ){
            header("Torquelink-Access", token)
        }.decode<Pageable<Groups.GroupDto>>()
    }
}