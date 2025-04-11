package nl.torquelink.presentation.screens.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nl.torquelink.presentation.navigation.Destinations
import org.koin.compose.koinInject

fun NavGraphBuilder.loginScreenNavGraph(
    snackBarHostState: SnackbarHostState
) {
    composable<Destinations.LoginDestination> {
        val viewModel: LoginScreenViewModel = koinInject()
        val viewModelState by viewModel.state.collectAsStateWithLifecycle()

        LoginScreen(
            state = viewModelState,
            onEvent = viewModel::dispatch,
            snackBarHostState = snackBarHostState,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}