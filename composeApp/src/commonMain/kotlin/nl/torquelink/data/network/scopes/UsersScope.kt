package nl.torquelink.data.network.scopes

import io.ktor.client.plugins.resources.post
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import nl.torquelink.data.network.client.httpClient
import nl.torquelink.data.network.result.Result
import nl.torquelink.data.network.result.decode
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.profile.UserProfiles
import nl.torquelink.shared.routing.subRouting.TorqueLinkUserRoutingV1

object UsersScope {
    suspend fun createUserProfile(
        token: String,
        body: UserProfiles.UserProfileCreateDto
    ) : Result<UserProfiles.UserProfileDto> {
        return httpClient.post(
            TorqueLinkUserRoutingV1.Profiles()
        ){
            setBody(body)
            header("Torquelink-Access", token)
        }.decode<UserProfiles.UserProfileDto>()
    }
}