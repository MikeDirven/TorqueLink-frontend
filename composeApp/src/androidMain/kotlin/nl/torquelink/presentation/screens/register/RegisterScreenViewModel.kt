package nl.torquelink.presentation.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.torquelink.presentation.navigation.Destinations
import nl.torquelink.presentation.navigation.navigator.Navigator

class RegisterScreenViewModel(
    private val navigator: Navigator
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
                    navigator.navigate(
                        Destinations.LoginDestination
                    ){
                        popUpTo(0){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}