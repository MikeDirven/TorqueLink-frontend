package nl.torquelink.presentation.screens.register

import nl.torquelink.presentation.screens.login.LoginScreenEvents

sealed interface RegisterScreenEvents {
    data class UsernameInputChanged(val input: String, val error: Boolean) : RegisterScreenEvents
    data class PasswordInputChanged(val input: String, val error: Boolean) : RegisterScreenEvents
    data class EmailInputChanged(val input: String, val error: Boolean) : RegisterScreenEvents

    data object OnCancelPressed: RegisterScreenEvents
    data object OnRegisterPressed: RegisterScreenEvents
}