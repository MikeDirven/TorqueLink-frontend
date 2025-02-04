package nl.torquelink.presentation.screens.login

sealed interface LoginScreenEvents {
    data class UsernameInputChanged(val input: String) : LoginScreenEvents
    data class PasswordInputChanged(val input: String) : LoginScreenEvents
}