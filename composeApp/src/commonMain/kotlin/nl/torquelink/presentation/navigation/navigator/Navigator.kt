package nl.torquelink.presentation.navigation.navigator

import androidx.navigation.NavOptionsBuilder
import kotlinx.coroutines.flow.Flow
import nl.torquelink.presentation.navigation.model.Destination
import nl.torquelink.presentation.navigation.model.NavigationAction

interface Navigator {
    val navigationActions: Flow<NavigationAction>

    suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )

    suspend fun navigateUp()
}