package nl.torquelink.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.SuccessResult
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import nl.torquelink.presentation.snackbar.model.SnackBar

class RegisterScreenViewModel(
    private val navigator: Navigator,
    private val snackBarController: SnackBarController,
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterScreenState())

    val state = _state.asStateFlow()

    private fun checkInputs() {
        // TODO: Check inputs when exceptions are possible in the state
    }

    fun dispatch(event: RegisterScreenEvents) {
        when(event) {
            is RegisterScreenEvents.PasswordInputChanged -> _state.update {
                it.copy(passwordInput = event.input, hasError = event.error)
            }
            is RegisterScreenEvents.UsernameInputChanged -> _state.update {
                it.copy(usernameInput = event.input, hasError = event.error)
            }
            is RegisterScreenEvents.EmailInputChanged -> _state.update {
                it.copy(emailInput = event.input, hasError = event.error)
            }
            is RegisterScreenEvents.OnCancelPressed -> {
                viewModelScope.launch {
                    navigator.navigateUp()
                }
            }
            is RegisterScreenEvents.OnRegisterPressed -> {
                viewModelScope.launch {
                    val response = authenticationRepository.register(
                        username = state.value.usernameInput,
                        password = state.value.passwordInput,
                        email = state.value.emailInput
                    )

                    when(response) {
                        is SuccessResult -> {
                            snackBarController.create(
                                SnackBar(
                                    "Registration success, Check your email for verification."
                                )
                            )
                            navigator.navigate(
                                Destinations.LoginDestination
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
}