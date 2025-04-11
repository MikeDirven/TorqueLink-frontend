package nl.torquelink.presentation.screens.profile.create

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nl.torquelink.presentation.navigation.Destinations
import org.koin.compose.koinInject

fun NavGraphBuilder.profileCreateScreenNavGraph(
    snackBarHostState: SnackbarHostState
) {
    composable<Destinations.CreateProfileDestination> {
        val viewModel: ProfileCreateScreenViewModel = koinInject<ProfileCreateScreenViewModel>()
        val viewModelState by viewModel.state.collectAsStateWithLifecycle()

        ProfileCreateScreen(
            state = viewModelState,
            onEvent = viewModel::dispatch,
            snackBarHostState = snackBarHostState,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}