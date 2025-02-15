package nl.torquelink.presentation.screens.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.SuccessResult
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import nl.torquelink.presentation.snackbar.model.SnackBar

class TimeLineScreenViewModel(
    private val navigator: Navigator,
    private val preferences: PreferencesRepository,
    private val snackBarController: SnackBarController
) : ViewModel() {
    private val _state = MutableStateFlow(TimeLineScreenState())

    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            // Redirect to login if not logged in
            checkAccessToken()

            // Get user profile for image
            preferences.getProfile()?.let {

            } ?: snackBarController.create(
                SnackBar(
                    "Unable to load profile!"
                )
            )
        }
    }

    fun dispatch(event: TimeLineScreenEvents) {

    }

    private suspend fun checkAccessToken() {
        if(preferences.getAccessToken().isNullOrBlank())
            navigator.navigate(
                Destinations.LoginDestination
            ) {
                popUpTo(0) {
                    inclusive = true
                }
            }
    }
}