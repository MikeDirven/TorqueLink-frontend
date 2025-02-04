package nl.torquelink.presentation.screens.login

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

//fun NavGraphBuilder.loginScreenNavGraph(
//    windowSizeClass: WindowWidthSizeClass
//) {
//    composable<Destinations.LoginDestination> {
//        val viewModel: LoginScreenViewModel = viewModel()
//        val viewModelState by viewModel.state.collectAsStateWithLifecycle()
//
//        LoginScreen(
//            state = viewModelState,
//            onEvent = viewModel::dispatch,
//            windowSizeClass = windowSizeClass,
//            modifier = Modifier
//                .fillMaxSize()
//        )
//    }
//}