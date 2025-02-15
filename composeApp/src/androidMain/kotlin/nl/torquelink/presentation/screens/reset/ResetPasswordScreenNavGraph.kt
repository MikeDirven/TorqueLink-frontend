package nl.torquelink.presentation.screens.reset

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import nl.torquelink.presentation.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.resetPasswordNavGraph(
    windowSizeClass: WindowWidthSizeClass,
    snackBarHostState: SnackbarHostState
) {
    composable<Destinations.ResetPasswordDestination>(
        deepLinks = listOf(
            navDeepLink<Destinations.ResetPasswordDestination>(
                basePath = "http://torquelink.nl/password/reset"
            )
        )
    ) {
        val resetToken = it.toRoute<Destinations.ResetPasswordDestination>().resetToken
        val viewModel: ResetPasswordScreenViewModel = koinViewModel()
        val viewModelState by viewModel.state.collectAsStateWithLifecycle()

        ResetPasswordScreen(
            state = viewModelState,
            onEvent = viewModel::dispatch,
            windowSizeClass = windowSizeClass,
            snackBarHostState = snackBarHostState,
            resetToken = resetToken,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}