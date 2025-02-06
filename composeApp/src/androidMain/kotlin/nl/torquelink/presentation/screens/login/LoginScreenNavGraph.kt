package nl.torquelink.presentation.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import nl.torquelink.presentation.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.loginScreenNavGraph(
    windowSizeClass: WindowWidthSizeClass
) {
    composable<Destinations.LoginDestination>(
        deepLinks = listOf(
            navDeepLink { uriPattern = "http://torquelink.nl" }
        )
    ) {
        val viewModel: LoginScreenViewModel = koinViewModel()
        val viewModelState by viewModel.state.collectAsStateWithLifecycle()

        LoginScreen(
            state = viewModelState,
            onEvent = viewModel::dispatch,
            windowSizeClass = windowSizeClass,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}