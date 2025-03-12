package nl.torquelink.presentation.language.interfaces.sub

interface Profile {
    // generic
    val title: String

    // Fields
    val firstNameFieldLabel: String
    val lastNameFieldLabel: String
    val dateOfBirthFieldLabel: String
    val phoneNumberFieldLabel: String
    val countryFieldLabel: String
    val cityFieldLabel: String

    // Buttons
    val createProfileButton: String
}