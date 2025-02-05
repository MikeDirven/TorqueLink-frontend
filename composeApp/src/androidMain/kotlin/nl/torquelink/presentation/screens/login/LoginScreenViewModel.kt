package nl.torquelink.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator

class LoginScreenViewModel(
    private val navigator: Navigator
) : ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())

    val state = _state.asStateFlow()

    fun dispatch(event: LoginScreenEvents) {
        when(event) {
            is LoginScreenEvents.PasswordInputChanged -> _state.update {
                it.copy(passwordInput = event.input, hasError = event.error)
            }
            is LoginScreenEvents.UsernameInputChanged -> _state.update {
                it.copy(usernameInput = event.input, hasError = event.error)
            }
            is LoginScreenEvents.OnCreateAccountPressed -> {
                viewModelScope.launch {
                    navigator.navigate(
                        Destinations.RegisterDestination
                    )
                }
            }
        }
    }
}