package nl.torquelink.domain.enums

import org.jetbrains.compose.resources.DrawableResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.cars
import torquelink.composeapp.generated.resources.events
import torquelink.composeapp.generated.resources.groups
import torquelink.composeapp.generated.resources.home

enum class BaseScreenTabs(
    val icon: DrawableResource
) {
    HOME(
        icon = Res.drawable.home
    ),
    EVENTS(
        icon = Res.drawable.events
    ),
    GROUPS(
        icon = Res.drawable.groups
    ),
    CARS(
        icon = Res.drawable.cars
    ),
    PROFILE(
        icon = Res.drawable.cars
    )
}