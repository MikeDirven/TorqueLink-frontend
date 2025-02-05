package nl.torquelink.presentation.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import nl.torquelink.presentation.language.enums.Languages
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.mappings.dutch.DutchMapping

@Composable
fun useLanguage(
    languageOverride: Languages? = null
) : Language {
    val currentLanguage by remember {
        derivedStateOf {
            languageOverride?.getMapping ?: DutchMapping
        }
    }

    return currentLanguage
}