package nl.torquelink.presentation.screens.group.overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nl.torquelink.presentation.group.overview.GroupOverviewScreenViewModel
import nl.torquelink.presentation.navigation.Destinations
import org.koin.compose.koinInject

fun NavGraphBuilder.groupOverviewScreenNavGraph(
    snackBarHostState: SnackbarHostState
) {
    composable<Destinations.Groups.Overview> {
        val viewModel: GroupOverviewScreenViewModel = koinInject<GroupOverviewScreenViewModel>()
        val viewModelState by viewModel.state.collectAsStateWithLifecycle()

        GroupOverviewScreen(
            state = viewModelState,
            onEvent = viewModel::dispatch,
            snackBarHostState = snackBarHostState,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}