package nl.torquelink.domain.enums

import nl.torquelink.presentation.navigation.Destinations
import org.jetbrains.compose.resources.DrawableResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.cars
import torquelink.composeapp.generated.resources.events
import torquelink.composeapp.generated.resources.groups
import torquelink.composeapp.generated.resources.home
import torquelink.composeapp.generated.resources.logo
import torquelink.composeapp.generated.resources.logo_dark
import torquelink.composeapp.generated.resources.profile

enum class BaseScreenTabs(
    val icon: DrawableResource,
    val isAvatar: Boolean = false,
    val destination: Destinations
) {
    HOME(
        icon = Res.drawable.home,
        destination = Destinations.TimeLine
    ),
    EVENTS(
        icon = Res.drawable.events,
        destination = Destinations.TimeLine
    ),
    GROUPS(
        icon = Res.drawable.groups,
        destination = Destinations.Groups.Overview
    ),
    CARS(
        icon = Res.drawable.cars,
        destination = Destinations.TimeLine
    ),
    PROFILE(
        icon = Res.drawable.profile,
        destination = Destinations.TimeLine,
        isAvatar = true
    )
}