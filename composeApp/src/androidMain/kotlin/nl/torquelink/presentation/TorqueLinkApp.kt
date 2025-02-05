package nl.torquelink.presentation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.TorqueLinkNavController
import nl.torquelink.presentation.screens.login.loginScreenNavGraph
import nl.torquelink.presentation.screens.register.registerScreenNavGraph

@Composable
fun TorqueLinkApp(
    windowSizeClass: WindowWidthSizeClass,
) {
    val navController = rememberNavController()

    TorqueLinkNavController(
        navController = navController,
        startDestination = Destinations.LoginDestination
    ) {
        loginScreenNavGraph(windowSizeClass = windowSizeClass)
        registerScreenNavGraph(windowSizeClass = windowSizeClass)
    }
}