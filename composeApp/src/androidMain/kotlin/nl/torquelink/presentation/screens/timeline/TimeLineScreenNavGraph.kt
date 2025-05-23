package nl.torquelink.presentation.screens.timeline

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nl.torquelink.domain.utils.firebase.FirebaseUtil
import nl.torquelink.presentation.navigation.Destinations
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.timeLineScreenNavGraph(
    snackBarHostState: SnackbarHostState
) {
    composable<Destinations.TimeLine> {
        val viewModel: TimeLineScreenViewModel = koinViewModel()
        val viewModelState by viewModel.state.collectAsStateWithLifecycle()

        FirebaseUtil.InitializeMessaging()

        TimeLineScreen(
            state = viewModelState,
            onEvent = viewModel::dispatch,
            snackBarHostState = snackBarHostState,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}