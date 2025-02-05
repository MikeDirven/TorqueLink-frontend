package nl.torquelink.presentation.language.enums

import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.mappings.dutch.DutchMapping

enum class Languages(
    val languageCode: String,
    val displayName: String,
    val regionCode: String
) {
    NL("nl", "Nederlands", "NL");
//    EN("en", "English", "US"), // Standaard US Engels, kan aangepast worden naar GB of ander regio
//    DE("de", "Deutsch", "DE");

    val getMapping: Language
        get() {
            return when(this) {
                NL -> DutchMapping
            }
        }
}