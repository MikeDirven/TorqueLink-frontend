package nl.torquelink.presentation.language.mappings.sub

import nl.torquelink.presentation.language.interfaces.sub.Login

object LoginMapping : Login {
    override val userFieldLabel: String = "Username/Email"
    override val passwordFieldLabel: String = "Password"

    override val emptyUserInput: String = "Username or email cannot be empty!"
    override val emptyPasswordInput: String = "Password cannot be empty"
}