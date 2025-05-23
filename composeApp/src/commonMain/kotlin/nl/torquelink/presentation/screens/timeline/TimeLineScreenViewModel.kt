package nl.torquelink.presentation.screens.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.screens.group.information.GroupInformationScreenState
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.models.profile.UserProfiles

class TimeLineScreenViewModel(
    private val navigator: Navigator,
    private val preferences: PreferencesRepository,
    private val snackBarController: SnackBarController
) : ViewModel() {
    private val _profileState = MutableStateFlow<UserProfiles.UserProfileWithSettingsDto?>(null)
    private val _timelineDataState = MutableStateFlow<List<Any>?>(null)
    private val _errorState = MutableStateFlow<String?>(null)

    val state = combine(_errorState, _profileState, _timelineDataState){ error, profile, data ->
        when {
            error != null -> TimeLineScreenState.ErrorScreenState(profile)
            data != null -> TimeLineScreenState.ScreenStateWithData(profile, data)
            else -> TimeLineScreenState.LoadingScreenState(profile)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 2000),
        initialValue = TimeLineScreenState.LoadingScreenState(_profileState.value)
    )

    init {
        viewModelScope.launch {
            // Redirect to login if not logged in
            checkAccessToken()

            preferences.getProfile()?.let { newProfile ->
                _profileState.update {
                    newProfile
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