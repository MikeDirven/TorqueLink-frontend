package nl.torquelink.presentation.navigation

import kotlinx.serialization.Serializable
import nl.torquelink.presentation.navigation.model.Destination

sealed interface Destinations : Destination {
    @Serializable
    data object LoginDestination : Destinations

    @Serializable
    data object RegisterDestination : Destinations
}