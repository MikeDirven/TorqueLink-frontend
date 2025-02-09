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
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator
import nl.torquelink.presentation.snackbar.controller.SnackBarController

class LoginScreenViewModel(
    private val navigator: Navigator,
    private val snackBarController: SnackBarController,
    private val authenticationRepository: AuthenticationRepository,
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
                        is SuccessResult -> {}
                        is ErrorResult -> {}
                    }
                }
            }
        }
    }
}