package nl.torquelink.presentation.screens.login

data class LoginScreenState(
    val hasError: Boolean = false,
    val usernameInput: String = "",
    val passwordInput: String = ""
)
