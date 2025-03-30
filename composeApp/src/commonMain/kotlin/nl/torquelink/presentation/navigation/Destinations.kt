package nl.torquelink.presentation.navigation

import kotlinx.serialization.Serializable
import nl.torquelink.presentation.navigation.model.Destination

sealed interface Destinations : Destination {
    @Serializable
    data object LoginDestination : Destinations

    @Serializable
    data object RegisterDestination : Destinations

    @Serializable
    data object CreateProfileDestination: Destinations

    @Serializable
    data class ResetPasswordDestination(
        val resetToken: String? = null
    ): Destinations

    @Serializable
    data object TimeLine: Destinations

    sealed interface Groups : Destinations {
        @Serializable
        data object Overview : Groups
    }
}