package nl.torquelink.presentation.language.mappings.dutch.sub

import nl.torquelink.presentation.language.interfaces.sub.Profile

object ProfileMapping : Profile {
    // Generic
    override val title: String = "Create Profile"

    // Fields
    override val firstNameFieldLabel: String = "Voornaam"
    override val lastNameFieldLabel: String = "Achternaam"
    override val dateOfBirthFieldLabel: String = "Geboorte datum"
    override val phoneNumberFieldLabel: String = "Telefoon nummer"
    override val countryFieldLabel: String = "Land"
    override val cityFieldLabel: String = "Stad"

    // Buttons
    override val createProfileTitle: String = "Profiel maken"
    override val createProfileButton: String = "Aanmaken"
}