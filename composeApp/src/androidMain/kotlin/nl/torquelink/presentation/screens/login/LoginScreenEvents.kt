package nl.torquelink.presentation.screens.login

sealed interface LoginScreenEvents {
    data class UsernameInputChanged(val input: String, val error: Boolean = false) : LoginScreenEvents
    data class PasswordInputChanged(val input: String, val error: Boolean = false) : LoginScreenEvents

    data object TryLoginWithRememberToken : LoginScreenEvents

    data object OnCreateAccountPressed : LoginScreenEvents
    data object OnLoginButtonPressed: LoginScreenEvents
    data object OnForgotPasswordPressed : LoginScreenEvents

}