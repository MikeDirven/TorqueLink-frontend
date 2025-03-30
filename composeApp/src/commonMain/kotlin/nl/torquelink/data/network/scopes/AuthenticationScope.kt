package nl.torquelink.data.network.scopes

import io.ktor.client.plugins.resources.post
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import nl.torquelink.data.network.client.httpClient
import nl.torquelink.data.network.result.EmptyResult
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

    suspend fun register(body: RegistrationRequests) : EmptyResult {
        return httpClient.post(
            TorqueLinkAuthRouting.Register()
        ){
            setBody(body)
        }.decode<Unit>()
    }

    suspend fun requestPasswordReset(body: ResetPasswordRequests) : EmptyResult {
        return httpClient.post(
            TorqueLinkAuthRouting.Password.Reset()
        ){
            setBody(body)
        }.decode<Unit>()
    }

    suspend fun setNotificationToken(token: String, body: String) : EmptyResult {
        return httpClient.post(
            TorqueLinkAuthRouting.Notifications.Token()
        ){
            setBody(body)
            header("Torquelink-Access", token)
        }.decode<Unit>()
    }
}