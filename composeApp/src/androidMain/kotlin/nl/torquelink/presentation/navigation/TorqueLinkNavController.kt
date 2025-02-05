package nl.torquelink.presentation.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import nl.torquelink.domain.utils.FlowUtil.ObserveAsEvents
import nl.torquelink.presentation.navigation.model.Destination
import nl.torquelink.presentation.navigation.model.NavigationAction
import nl.torquelink.presentation.navigation.navigator.Navigator
import org.koin.compose.koinInject

@Composable
fun TorqueLinkNavController(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Destination,
    builder: NavGraphBuilder.() -> Unit
) {
    val navigator = koinInject<Navigator>()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
            is NavigationAction.Navigate -> navController.navigate(
                action.destination,
                action.navOptions
            )

            is NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        builder = builder
    )
}