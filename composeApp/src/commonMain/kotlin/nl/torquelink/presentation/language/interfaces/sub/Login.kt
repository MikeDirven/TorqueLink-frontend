package nl.torquelink.presentation.language.interfaces.sub

interface Login {
    val userFieldLabel: String
    val passwordFieldLabel: String

    // Exceptions
    val emptyUserInput: String
    val emptyPasswordInput: String
}