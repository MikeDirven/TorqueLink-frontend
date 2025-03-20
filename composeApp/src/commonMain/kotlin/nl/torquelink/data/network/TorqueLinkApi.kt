package nl.torquelink.data.network

import nl.torquelink.data.network.scopes.AuthenticationScope
import nl.torquelink.data.network.scopes.UsersScope

object TorqueLinkApi{
    val authenticationApi by lazy {
        AuthenticationScope
    }
    val usersApi by lazy {
        UsersScope
    }
}