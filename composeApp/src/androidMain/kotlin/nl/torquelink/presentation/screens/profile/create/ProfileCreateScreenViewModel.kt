@file:OptIn(ExperimentalMaterial3Api::class)

package nl.torquelink.presentation.screens.profile.create

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.SuccessResult
import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.domain.repositories.UsersRepository
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import nl.torquelink.presentation.snackbar.model.SnackBar
import nl.torquelink.shared.models.profile.UserProfiles

class ProfileCreateScreenViewModel(
    private val navigator: Navigator,
    private val preferences: PreferencesRepository,
    private val usersRepository: UsersRepository,
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

    fun dispatch(event: ProfileCreateScreenEvents) {
        when(event) {
            is ProfileCreateScreenEvents.CityInputChanged -> {
                _state.update {
                    it.copy(
                        hasError = event.error,
                        cityInput = event.input,
                        cityIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.CountryInputChanged -> {
                _state.update {
                    it.copy(
                        hasError = event.error,
                        countryInput = event.input,
                        countryIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.DateOfBirthInputChanged -> {
                _state.update {
                    it.copy(
                        hasError = event.error,
                        dateOfBirthInput = event.input,
                        dateOfBirthIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.FirstNameInputChanged -> {
                _state.update {
                    it.copy(
                        hasError = event.error,
                        firstNameInput = event.input,
                        firstNameIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.LastNameInputChanged -> {
                _state.update {
                    it.copy(
                        hasError = event.error,
                        lastNameInput = event.input,
                        lastNameIsPrivate = event.private
                    )
                }
            }
            is ProfileCreateScreenEvents.PhoneNumberInputChanged -> {
                _state.update {
                    it.copy(
                        hasError = event.error,
                        phoneNumberInput = event.input,
                        phoneNumberIsPrivate = event.private
                    )
                }
            }

            is ProfileCreateScreenEvents.CreateProfile -> {
                viewModelScope.launch {
                    val response = usersRepository.createUserProfile(
                        UserProfiles.UserProfileCreateDto(
                            firstName = state.value.firstNameInput,
                            lastName = state.value.lastNameInput,
                            dateOfBirth = state.value.dateOfBirthInput.toString(),
                            phoneNumber = state.value.phoneNumberInput,
                            city = state.value.cityInput,
                            country = state.value.countryInput,
                            emailIsPublic = true,
                            firstNameIsPublic = state.value.firstNameIsPrivate,
                            lastNameIsPublic = state.value.lastNameIsPrivate,
                            dateOfBirthIsPublic = state.value.dateOfBirthIsPrivate,
                            phoneNumberIsPublic = state.value.phoneNumberIsPrivate,
                            countryIsPublic = state.value.countryIsPrivate,
                            cityIsPublic = state.value.cityIsPrivate,
                        )
                    )

                    when(response) {
                        is SuccessResult -> {
                            snackBarController.create(
                                SnackBar(
                                    "Profile creating success"
                                )
                            )
                            navigator.navigate(
                                Destinations.TimeLine
                            ){
                                popUpTo(0){
                                    inclusive = true
                                }
                            }
                        }
                        is ErrorResult.Error -> {
                            snackBarController.create(
                                SnackBar(
                                    response.exception.localizedMessage
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