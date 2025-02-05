package nl.torquelink.presentation.language.mappings.dutch.sub

import nl.torquelink.presentation.language.interfaces.sub.Login

object LoginMapping : Login {
    override val loginButton: String = "Inloggen"
    override val createAccountButton: String = "Maak een account"
    override val forgottenPasswordButtons: String = "Wachtwoord vergeten?"
}