package nl.torquelink.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.torquelink.domain.utils.coroutines.FlowUtil.ObserveAsEvents
import nl.torquelink.presentation.navigation.model.Destination
import nl.torquelink.presentation.navigation.model.NavigationAction
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import org.koin.compose.koinInject

@Composable
fun TorqueLinkNavController(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    startDestination: Destination,
    builder: NavGraphBuilder.(SnackbarHostState) -> Unit
) {
    val navigator = koinInject<Navigator>()
    val snackBarController = koinInject<SnackBarController>()

    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
            is NavigationAction.Navigate -> navController.navigate(
                action.destination,
                action.navOptions
            )

            is NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }

    ObserveAsEvents(flow = snackBarController.snackBars) { snackBar ->
        CoroutineScope(Dispatchers.IO).launch {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = snackBar.message,
                actionLabel = snackBar.actionLabel,
                withDismissAction = true,
                duration = snackBar.duration
            )
            when (snackBarResult) {
                SnackbarResult.ActionPerformed -> snackBar.onActionPerformed?.invoke()
                SnackbarResult.Dismissed -> snackBar.onDismiss?.invoke()
            }
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        builder = {
            builder(snackBarHostState)
        }
    )
}