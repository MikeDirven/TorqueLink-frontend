package nl.torquelink.presentation

import android.content.Intent
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.TorqueLinkNavController
import nl.torquelink.presentation.screens.login.loginScreenNavGraph
import nl.torquelink.presentation.screens.register.registerScreenNavGraph

@Composable
fun TorqueLinkApp(
    windowSizeClass: WindowWidthSizeClass,
    deepLinkIntent: Intent? = null
) {
    val navController = rememberNavController()

    LaunchedEffect(deepLinkIntent) {
        if (deepLinkIntent!= null) {
            navController.handleDeepLink(deepLinkIntent)
        }
    }

    TorqueLinkNavController(
        navController = navController,
        startDestination = Destinations.LoginDestination
    ) {
        loginScreenNavGraph(windowSizeClass = windowSizeClass)
        registerScreenNavGraph(windowSizeClass = windowSizeClass)
    }
}