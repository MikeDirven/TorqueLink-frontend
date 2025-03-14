package nl.torquelink.presentation.language.interfaces.sub

interface Generic {
    val userFieldLabel: String
    val userAndEmailFieldLabel: String
    val passwordFieldLabel: String
    val emailFieldLabel: String

    // Buttons
    val cancelButton: String
    val confirmButton: String

    // Exceptions
    val emptyUserInput: String

    val passwordInvalid: String
    val emptyPasswordInput: String
    val passwordToShort: String
    val passwordNeedsLowercaseCharacter: String
    val passwordNeedsUppercaseCharacter: String
    val passwordNeedsSpecialCharacter: String

    val emptyEmailInput: String
    val emailNotValid: String

    val emptyFirstNameInput: String
    val emptyLastNameInput: String
    val emptyPhoneNumberInput: String
    val phoneNumberNotValid: String
    val emptyCityInput: String
}