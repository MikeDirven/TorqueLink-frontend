package nl.torquelink.presentation.screens.login

data class LoginScreenState(
    val hasError: Boolean = true,
    val userInput: String = "",
    val passwordInput: String = ""
)
