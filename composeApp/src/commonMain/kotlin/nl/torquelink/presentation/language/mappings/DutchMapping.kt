package nl.torquelink.presentation.language.mappings

import nl.torquelink.presentation.language.enums.Languages
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.interfaces.sub.Login
import nl.torquelink.presentation.language.mappings.sub.LoginMapping

object DutchMapping : Language {
    override val locale: Languages = Languages.NL

    override val loginScreen: Login by lazy { LoginMapping }
}