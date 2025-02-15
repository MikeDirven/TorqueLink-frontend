package nl.torquelink.domain.validators

import androidx.compose.runtime.Composable
import nl.torquelink.domain.validators.interfaces.Validator
import nl.torquelink.domain.validators.interfaces.ValidatorResult
import nl.torquelink.presentation.language.interfaces.Language

class EmailAddressValidator(
    override val language: Language
) : Validator {
    private val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+\$")

    override fun validate(input: CharSequence) : ValidatorResult {
        return when {
            emailRegex.matches(input) -> ValidatorResult.Valid
            input.isBlank() -> ValidatorResult.Invalid(language.generic.emptyEmailInput)
            else -> ValidatorResult.Invalid(language.generic.emailNotValid)
        }
    }
}