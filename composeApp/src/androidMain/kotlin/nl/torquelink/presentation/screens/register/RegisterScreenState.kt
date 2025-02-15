package nl.torquelink.presentation.screens.register

data class RegisterScreenState(
    val hasError: Boolean = true,
    val usernameInput: String = "",
    val passwordInput: String = "",
    val emailInput: String = ""
)
