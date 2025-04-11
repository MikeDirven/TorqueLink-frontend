package nl.torquelink.presentation.screens.reset

data class ResetPasswordScreenState(
    val hasError: Boolean = true,
    val userInput: String = "",
    val emailInput: String = "",
    val newPasswordInput: String = ""
)
