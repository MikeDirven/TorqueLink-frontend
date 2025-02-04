package nl.torquelink.presentation.language.interfaces

import nl.torquelink.presentation.language.enums.Languages
import nl.torquelink.presentation.language.interfaces.sub.Login

interface Language {
    val locale: Languages
    val loginScreen: Login
}