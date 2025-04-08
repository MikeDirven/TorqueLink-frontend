package nl.torquelink.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.TorqueLinkNavController
import nl.torquelink.presentation.navigation.navigator.DefaultNavigator
import nl.torquelink.presentation.snackbar.controller.DefaultSnackBarController

@Composable
fun TorqueLinkApp() {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    TorqueLinkNavController(
        navigator = DefaultNavigator,
        snackBarController = DefaultSnackBarController,
        navController = navController,
        startDestination = Destinations.LoginDestination,
        snackBarHostState = snackBarHostState
    ) { snackBarState ->

    }
}