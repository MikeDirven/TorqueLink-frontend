@file:OptIn(ExperimentalMaterial3Api::class)

package nl.torquelink.presentation.screens.profile.create

import androidx.compose.material3.ExperimentalMaterial3Api
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

class ProfileCreateScreenViewModel(
    private val navigator: Navigator,
    private val preferences: PreferencesRepository,
    private val snackBarController: SnackBarController
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileCreateScreenState())

    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            // Redirect to login if not logged in
            checkAccessToken()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun dispatch(event: ProfileCreateScreenEvents) {
        when(event) {
            is ProfileCreateScreenEvents.CityInputChanged -> {
                _state.update {
                    it.copy(
                        cityInput = event.input,
                        cityIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.CountryInputChanged -> {
                _state.update {
                    it.copy(
                        countryInput = event.input,
                        countryIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.DateOfBirthInputChanged -> {
                _state.update {
                    it.copy(
                        dateOfBirthInput = event.input,
                        dateOfBirthIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.FirstNameInputChanged -> {
                _state.update {
                    it.copy(
                        firstNameInput = event.input,
                        firstNameIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.LastNameInputChanged -> {
                _state.update {
                    it.copy(
                        lastNameInput = event.input,
                        lastNameIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.PhoneNumberInputChanged -> {
                _state.update {
                    it.copy(
                        phoneNumberInput = event.input,
                        phoneNumberIsPrivate = event.private
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