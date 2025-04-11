package nl.torquelink.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.TorqueLinkNavController
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.screens.group.overview.groupOverviewScreenNavGraph
import nl.torquelink.presentation.screens.login.loginScreenNavGraph
import nl.torquelink.presentation.screens.profile.create.profileCreateScreenNavGraph
import nl.torquelink.presentation.screens.register.registerScreenNavGraph
import nl.torquelink.presentation.screens.reset.resetPasswordNavGraph
import nl.torquelink.presentation.screens.timeline.timeLineScreenNavGraph
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import org.koin.compose.koinInject

@Composable
fun TorqueLinkApp() {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    TorqueLinkNavController(
        navigator = koinInject<Navigator>(),
        snackBarController = koinInject<SnackBarController>(),
        navController = navController,
        startDestination = Destinations.LoginDestination,
        snackBarHostState = snackBarHostState
    ) { snackBarState ->
        loginScreenNavGraph(snackBarState)
        registerScreenNavGraph(snackBarState)
        resetPasswordNavGraph(snackBarState)
        profileCreateScreenNavGraph(snackBarState)

        timeLineScreenNavGraph(snackBarState)

        groupOverviewScreenNavGraph(snackBarState)
    }
}