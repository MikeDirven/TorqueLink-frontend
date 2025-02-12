package nl.torquelink.presentation.screens.login

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

class LoginScreenViewModel(
    private val navigator: Navigator,
    private val snackBarController: SnackBarController,
    private val authenticationRepository: AuthenticationRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())

    val state = _state.asStateFlow()

    private fun isEmailLogin(input: String): Boolean {
        val pattern = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
        val matcher = pattern.matchEntire(input)
        return matcher != null
    }

    fun dispatch(event: LoginScreenEvents) {
        when(event) {
            is LoginScreenEvents.PasswordInputChanged -> _state.update {
                it.copy(passwordInput = event.input, hasError = event.error)
            }
            is LoginScreenEvents.UsernameInputChanged -> _state.update {
                it.copy(userInput = event.input, hasError = event.error)
            }
            is LoginScreenEvents.OnCreateAccountPressed -> {
                viewModelScope.launch {
                    navigator.navigate(
                        Destinations.RegisterDestination
                    )
                }
            }

            is LoginScreenEvents.TryLoginWithRememberToken -> {

                viewModelScope.launch {
                    preferencesRepository.getRememberToken()?.let { token ->
                        val response = authenticationRepository.loginByRememberToken(
                            token = token
                        )

                        when(response){
                            is SuccessResult.WithBody -> {
                                preferencesRepository.saveTokenInformation(
                                    response.data
                                )
//                            navigator.navigate(
////                                Destinations.HomeDestination
//                            ) {
//                                popUpTo(Destinations.LoginDestination) {
//                                    inclusive = true
//                                }
//                            }
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

            is LoginScreenEvents.OnLoginButtonPressed -> {
                viewModelScope.launch {
                    val response = when(isEmailLogin(state.value.userInput)){
                        true -> authenticationRepository.loginByEmail(
                            email = state.value.userInput,
                            password = state.value.passwordInput
                        )
                        false -> authenticationRepository.loginByUsername(
                            username = state.value.userInput,
                            password = state.value.passwordInput
                        )
                    }

                    when(response){
                        is SuccessResult.WithBody -> {
                            preferencesRepository.saveTokenInformation(
                                response.data
                            )
//                            navigator.navigate(
////                                Destinations.HomeDestination
//                            ) {
//                                popUpTo(Destinations.LoginDestination) {
//                                    inclusive = true
//                                }
//                            }
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