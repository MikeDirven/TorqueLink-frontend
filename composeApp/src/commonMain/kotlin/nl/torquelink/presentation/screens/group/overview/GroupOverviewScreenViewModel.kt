package nl.torquelink.presentation.screens.group.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.SuccessResult
import nl.torquelink.domain.exceptions.localMessage
import nl.torquelink.domain.repositories.GroupsRepository
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import nl.torquelink.presentation.snackbar.model.SnackBar

class GroupOverviewScreenViewModel(
    private val navigator: Navigator,
    private val preferences: PreferencesRepository,
    private val snackBarController: SnackBarController,
    private val groupsRepository: GroupsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(
        GroupOverviewScreenState()
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

            when(val groupsResponse = groupsRepository.getGroups()) {
                is SuccessResult.WithBody -> {
                    _state.update {
                        it.copy(
                            groupsData = groupsResponse.data.data
                        )
                    }
                }
                is ErrorResult.Error -> {
                    snackBarController.create(
                        SnackBar(
                            groupsResponse.exception.localMessage
                        )
                    )
                }

                else -> {
                    snackBarController.create(
                        SnackBar(
                            "Something went wrong"
                        )
                    )
                }
            }
        }
    }

    fun dispatch(event: GroupOverviewScreenEvents) {
        when(event) {
            is GroupOverviewScreenEvents.OnTabSwitch -> {
                viewModelScope.launch {
                    navigator.navigate(event.tab.destination)
                }
            }
            is GroupOverviewScreenEvents.OnGroupItemClicked -> {
                viewModelScope.launch {
                    navigator.navigate(
                        Destinations.Groups.Information(
                            event.groupId
                        )
                    )
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