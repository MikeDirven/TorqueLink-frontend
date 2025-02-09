package nl.torquelink.presentation.screens.register

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import nl.torquelink.presentation.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.registerScreenNavGraph(
    windowSizeClass: WindowWidthSizeClass,
    snackBarHostState: SnackbarHostState
) {
    composable<Destinations.RegisterDestination>(
        deepLinks = listOf(
            navDeepLink { uriPattern = "http://torquelink.nl/register" }
        )
    ) {
        val viewModel: RegisterScreenViewModel = koinViewModel()
        val viewModelState by viewModel.state.collectAsStateWithLifecycle()

        RegisterScreen(
            state = viewModelState,
            onEvent = viewModel::dispatch,
            windowSizeClass = windowSizeClass,
            snackBarHosState = snackBarHostState,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}