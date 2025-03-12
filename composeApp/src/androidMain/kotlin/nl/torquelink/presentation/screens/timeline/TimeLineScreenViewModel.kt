package nl.torquelink.presentation.screens.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
    private val _state = MutableStateFlow(
        TimeLineScreenState()
    )

    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            // Redirect to login if not logged in
            checkAccessToken()

            preferences.getProfile()?.let {
                _state.update {
                    it.copy(
                        profile = it.profile
                    )
                }
            }
        }
    }

    fun dispatch(event: TimeLineScreenEvents) {
        when(event) {
            is TimeLineScreenEvents.OnTabSwitch -> {
                viewModelScope.launch {
                    navigator.navigate(event.tab.destination)
                }
            }
        }
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