package nl.torquelink.data.network

import nl.torquelink.data.network.scopes.AuthenticationScope

object TorqueLinkApi{
    val authenticationApi by lazy {
        AuthenticationScope
    }
}