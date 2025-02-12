package nl.torquelink.presentation.screens.login

data class LoginScreenState(
    val hasError: Boolean = false,
    val userInput: String = "",
    val passwordInput: String = "",
    val errorMessage: String? = null
)
