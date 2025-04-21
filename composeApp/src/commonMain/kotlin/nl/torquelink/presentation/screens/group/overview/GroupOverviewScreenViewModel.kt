package nl.torquelink.presentation.screens.group.overview

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
import nl.torquelink.presentation.screens.group.information.GroupInformationScreenState
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import nl.torquelink.presentation.snackbar.model.SnackBar
import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.models.profile.UserProfiles

class GroupOverviewScreenViewModel(
    private val navigator: Navigator,
    private val preferences: PreferencesRepository,
    private val snackBarController: SnackBarController,
    private val groupsRepository: GroupsRepository
) : ViewModel() {
    private val _pagination = MutableStateFlow(Pagination(1, 10, true))
    private val _profileState = MutableStateFlow<UserProfiles.UserProfileWithSettingsDto?>(null)
    private val _groupDataState = MutableStateFlow<List<Groups.GroupDto>?>(null)
    private val _errorState = MutableStateFlow<String?>(null)

    val state = combine(_errorState, _profileState, _groupDataState, _pagination)
    { error, profile, data, pagination ->
        when {
            error != null -> GroupOverviewScreenState.ErrorScreenState(profile)
            data != null -> GroupOverviewScreenState.ScreenStateWithData(profile,pagination, data)
            else -> GroupOverviewScreenState.LoadingScreenState(profile)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 2000),
        initialValue = GroupOverviewScreenState.LoadingScreenState(_profileState.value)
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

            getGroupsData(_pagination.value)
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
            is GroupOverviewScreenEvents.LoadNextPage -> {
                viewModelScope.launch {
                    getGroupsData(
                        _pagination.value.copy(
                            page = _pagination.value.page + 1
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

    private fun getGroupsData(pagination: Pagination) {
        viewModelScope.launch {
            when(val groupsResponse = groupsRepository.getGroups(
                pagination.page,
                pagination.size
            )) {
                is SuccessResult.WithBody -> {
                    _pagination.update {
                        Pagination(
                            groupsResponse.data.currentPage,
                            groupsResponse.data.limit,
                            groupsResponse.data.hasNext
                        )
                    }
                    _groupDataState.update {
                        buildList {
                            _groupDataState.value?.let { addAll(it) }
                            addAll(groupsResponse.data.data)
                        }
                    }
                }
                is ErrorResult.Error -> {
                    _errorState.update {
                        groupsResponse.exception.localMessage
                    }
                    snackBarController.create(
                        SnackBar(
                            groupsResponse.exception.localMessage
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