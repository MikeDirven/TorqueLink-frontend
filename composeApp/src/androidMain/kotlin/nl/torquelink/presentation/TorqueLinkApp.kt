package nl.torquelink.presentation

import android.content.Intent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.domain.utils.firebase.FirebaseUtil
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.TorqueLinkNavController
import nl.torquelink.presentation.screens.group.overview.groupOverviewScreenNavGraph
import nl.torquelink.presentation.screens.login.loginScreenNavGraph
import nl.torquelink.presentation.screens.profile.create.profileCreateScreenNavGraph
import nl.torquelink.presentation.screens.register.registerScreenNavGraph
import nl.torquelink.presentation.screens.reset.resetPasswordNavGraph
import nl.torquelink.presentation.screens.timeline.timeLineScreenNavGraph
import org.koin.compose.koinInject

@Composable
fun TorqueLinkApp(
    windowSizeClass: WindowWidthSizeClass,
    deepLinkIntent: Intent? = null
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(deepLinkIntent) {
        if (deepLinkIntent!= null) {
            navController.handleDeepLink(deepLinkIntent)
        }
    }

    TorqueLinkNavController(
        navController = navController,
        startDestination = Destinations.LoginDestination,
        snackBarHostState = snackBarHostState
    ) { snackBarState ->
        loginScreenNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)
        registerScreenNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)
        resetPasswordNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)
        profileCreateScreenNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)

        timeLineScreenNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)

        groupOverviewScreenNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)
    }
}