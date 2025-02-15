package nl.torquelink.presentation.language.mappings.dutch.sub

import nl.torquelink.presentation.language.interfaces.sub.Generic
import nl.torquelink.presentation.language.interfaces.sub.Login

object GenericMapping : Generic {
    override val userFieldLabel: String = "Gebruikersnaam"
    override val userAndEmailFieldLabel: String = "Gebruikersnaam/Email"
    override val passwordFieldLabel: String = "Wachtwoord"
    override val emailFieldLabel: String = "Email"

    override val cancelButton: String = "Terug"

    override val emptyUserInput: String = "Gebruikersnaam of email mag niet leeg zijn."

    override val passwordInvalid: String = "Invalid password."
    override val emptyPasswordInput: String = "Wachtwoord mag niet leeg zijn."
    override val passwordToShort: String = "Wachtwoord moet langer zijn dan 8 tekens."
    override val passwordNeedsLowercaseCharacter: String = "Wachtwoord moet een kleine letter bevatten."
    override val passwordNeedsUppercaseCharacter: String = "Wachtwoord moet een hoofdletter bevatten."
    override val passwordNeedsSpecialCharacter: String = "Wachtwoord moet een speciaal teken bevatten."

    override val emptyEmailInput: String = "Email mag niet leeg zijn."
    override val emailNotValid: String = "Email is niet geldig."

}