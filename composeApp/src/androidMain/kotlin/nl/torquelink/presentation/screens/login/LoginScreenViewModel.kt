package nl.torquelink.presentation.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginScreenState())

    val state = _state.asStateFlow()

    fun dispatch(event: LoginScreenEvents) {
        when(event) {
            is LoginScreenEvents.PasswordInputChanged -> _state.update {
                it.copy(passwordInput = event.input)
            }
            is LoginScreenEvents.UsernameInputChanged -> _state.update {
                it.copy(usernameInput = event.input)
            }
        }
    }
}