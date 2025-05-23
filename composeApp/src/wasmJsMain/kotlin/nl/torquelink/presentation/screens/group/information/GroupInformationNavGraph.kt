package nl.torquelink.presentation.screens.group.information

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import nl.torquelink.presentation.screens.group.overview.GroupOverviewScreenViewModel
import nl.torquelink.presentation.navigation.Destinations
import org.koin.compose.koinInject

fun NavGraphBuilder.groupInformationScreenNavGraph(
    snackBarHostState: SnackbarHostState
) {
    composable<Destinations.Groups.Information> {
        val data = it.toRoute<Destinations.Groups.Information>()
        val viewModel: GroupInformationScreenViewModel = koinInject<GroupInformationScreenViewModel>()
        val viewModelState by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.dispatch(
                GroupInformationScreenEvents.GetGroupDetails(
                    data.groupId
                )
            )
        }

        GroupInformationScreen(
            state = viewModelState,
            onEvent = viewModel::dispatch,
            snackBarHostState = snackBarHostState,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}