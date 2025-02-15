package nl.torquelink.presentation.screens.reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.data.network.result.ErrorResult
import nl.torquelink.data.network.result.SuccessResult
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.snackbar.controller.SnackBarController
import nl.torquelink.presentation.snackbar.model.SnackBar

class ResetPasswordScreenViewModel(
    private val navigator: Navigator,
    private val snackBarController: SnackBarController,
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private val _state = MutableStateFlow(ResetPasswordScreenState())

    val state = _state.asStateFlow()

    fun dispatch(event: ResetPasswordScreenEvents) {
        when(event) {
            is ResetPasswordScreenEvents.EmailInputChanged -> _state.update {
                it.copy(emailInput = event.input, hasError = event.error)
            }
            is ResetPasswordScreenEvents.UsernameInputChanged -> _state.update {
                it.copy(userInput = event.input, hasError = event.error)
            }
            is ResetPasswordScreenEvents.NewPasswordInputChanged -> _state.update {
                it.copy(newPasswordInput = event.input, hasError = event.error)
            }
            is ResetPasswordScreenEvents.OnResetPasswordRequestPressed -> {
                viewModelScope.launch {
                    val response = authenticationRepository.requestPasswordReset(
                        username = state.value.userInput,
                        email = state.value.emailInput
                    )

                    when(response) {
                        is SuccessResult -> {
                            snackBarController.create(
                                SnackBar(
                                    "Password reset request sent, Check your email."
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
            is ResetPasswordScreenEvents.OnResetPasswordPressed -> {
                viewModelScope.launch {
                    val response = authenticationRepository.resetPassword(
                        token = event.token,
                        password = state.value.newPasswordInput
                    )

                    when(response) {
                        is SuccessResult -> {
                            snackBarController.create(
                                SnackBar(
                                    "Password reset success, you can now log in with the new password."
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
            is ResetPasswordScreenEvents.OnCancelPressed -> {
                viewModelScope.launch {
                    navigator.navigateUp()
                }
            }
        }
    }
}