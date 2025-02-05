package nl.torquelink.presentation.language.mappings.dutch

import nl.torquelink.presentation.language.enums.Languages
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.interfaces.sub.Generic
import nl.torquelink.presentation.language.interfaces.sub.Login
import nl.torquelink.presentation.language.interfaces.sub.Register
import nl.torquelink.presentation.language.mappings.dutch.sub.GenericMapping
import nl.torquelink.presentation.language.mappings.dutch.sub.LoginMapping
import nl.torquelink.presentation.language.mappings.dutch.sub.RegisterMapping

object DutchMapping : Language {
    override val locale: Languages = Languages.NL

    override val generic: Generic by lazy { GenericMapping }
    override val login: Login by lazy { LoginMapping }
    override val register: Register by lazy { RegisterMapping }
}