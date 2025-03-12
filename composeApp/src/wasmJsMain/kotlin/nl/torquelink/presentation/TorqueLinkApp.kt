//package nl.torquelink.presentation
//
//import android.content.Intent
//import androidx.compose.material3.SnackbarHostState
//import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.remember
//import androidx.navigation.compose.rememberNavController
//import nl.torquelink.domain.repositories.PreferencesRepository
//import nl.torquelink.presentation.navigation.Destinations
//import nl.torquelink.presentation.navigation.TorqueLinkNavController
//import nl.torquelink.presentation.screens.login.loginScreenNavGraph
//import nl.torquelink.presentation.screens.register.registerScreenNavGraph
//import nl.torquelink.presentation.screens.reset.resetPasswordNavGraph
//import nl.torquelink.presentation.screens.timeline.timeLineScreenNavGraph
//import org.koin.compose.koinInject
//
//@Composable
//fun TorqueLinkApp(
//    windowSizeClass: WindowWidthSizeClass,
//) {
//    val navController = rememberNavController()
//    val snackBarHostState = remember { SnackbarHostState() }
//
//    TorqueLinkNavController(
//        navController = navController,
//        startDestination = Destinations.LoginDestination,
//        snackBarHostState = snackBarHostState
//    ) { snackBarState ->
//        loginScreenNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)
//        registerScreenNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)
//        resetPasswordNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)
//
//        timeLineScreenNavGraph(windowSizeClass = windowSizeClass, snackBarHostState = snackBarState)
//    }
//}