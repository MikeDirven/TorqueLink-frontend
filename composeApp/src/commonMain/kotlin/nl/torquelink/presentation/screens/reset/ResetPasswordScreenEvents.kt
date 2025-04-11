package nl.torquelink.presentation.screens.reset

sealed interface ResetPasswordScreenEvents {
    data class UsernameInputChanged(val input: String, val error: Boolean = false) :
        ResetPasswordScreenEvents
    data class EmailInputChanged(val input: String, val error: Boolean = false) :
        ResetPasswordScreenEvents
    data class NewPasswordInputChanged(val input: String, val error: Boolean = false) :
        ResetPasswordScreenEvents

    data object OnResetPasswordRequestPressed : ResetPasswordScreenEvents
    data class OnResetPasswordPressed(val token: String) : ResetPasswordScreenEvents
    data object OnCancelPressed : ResetPasswordScreenEvents
}