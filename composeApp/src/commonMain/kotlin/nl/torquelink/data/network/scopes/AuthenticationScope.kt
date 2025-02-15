package nl.torquelink.data.network.scopes

import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import nl.torquelink.data.network.client.httpClient
import nl.torquelink.data.network.result.Result
import nl.torquelink.data.network.result.decode
import nl.torquelink.shared.models.auth.AuthenticationResponses
import nl.torquelink.shared.models.auth.LoginRequests
import nl.torquelink.shared.models.auth.RegistrationRequests
import nl.torquelink.shared.models.auth.ResetPasswordRequests
import nl.torquelink.shared.routing.subRouting.TorqueLinkAuthRouting

object AuthenticationScope {
    suspend fun login(body: LoginRequests) : Result<AuthenticationResponses> {
        return httpClient.post(
            TorqueLinkAuthRouting.Login()
        ){
            setBody(body)
        }.decode<AuthenticationResponses>()
    }

    suspend fun register(body: RegistrationRequests) : Result<Unit> {
        return httpClient.post(
            TorqueLinkAuthRouting.Register()
        ){
            setBody(body)
        }.decode<Unit>()
    }

    suspend fun requestPasswordReset(body: ResetPasswordRequests) : Result<Unit> {
        return httpClient.post(
            TorqueLinkAuthRouting.Password.Reset()
        ){
            setBody(body)
        }.decode<Unit>()
    }
}