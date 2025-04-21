package nl.torquelink.presentation.screens.group.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.SuccessResult
import nl.torquelink.domain.Pagination
import nl.torquelink.domain.exceptions.localMessage
import nl.torquelink.domain.repositories.GroupsRepository
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import nl.torquelink.presentation.snackbar.model.SnackBar
import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.models.profile.UserProfiles

class GroupInformationScreenViewModel(
    private val navigator: Navigator,
    private val preferences: PreferencesRepository,
    private val snackBarController: SnackBarController,
    private val groupsRepository: GroupsRepository
) : ViewModel() {
    private val _profileState = MutableStateFlow<UserProfiles.UserProfileWithSettingsDto?>(null)
    private val _groupDataState = MutableStateFlow<Groups.GroupWithDetailsDto?>(null)
    private val _errorState = MutableStateFlow<String?>(null)

    val state = combine(_errorState, _profileState, _groupDataState){ error, profile, data ->
        when {
            error != null -> GroupInformationScreenState.ErrorScreenState(profile)
            data != null -> GroupInformationScreenState.ScreenStateWithData(profile, data)
            else -> GroupInformationScreenState.LoadingScreenState(profile)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 2000),
        initialValue = GroupInformationScreenState.LoadingScreenState(_profileState.value)
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

    fun dispatch(event: GroupInformationScreenEvents) {
        when (event) {
            is GroupInformationScreenEvents.OnTabSwitch -> {
                viewModelScope.launch {
                    navigator.navigate(event.tab.destination)
                }
            }

            is GroupInformationScreenEvents.GetGroupDetails -> {
                viewModelScope.launch {
                    // Remove old data and error state to start loading screen state
                    _errorState.update { null }
                    _groupDataState.update { null }

                    // Get new data
                    when (val result = groupsRepository.getGroupDetails(event.groupId)) {
                        is SuccessResult.WithBody -> {
                            _groupDataState.update {
                                result.data
                            }
                        }

                        is ErrorResult.Error -> {
                            _errorState.update {
                                result.exception.localMessage
                            }

                            snackBarController.create(
                                SnackBar(
                                    result.exception.localMessage
                                )
                            )
                        }
                        else -> {
                            _errorState.update {
                                "Something went wrong"
                            }
                            snackBarController.create(
                                SnackBar(
                                    "Something went wrong"
                                )
                            )
                        }
                    }
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