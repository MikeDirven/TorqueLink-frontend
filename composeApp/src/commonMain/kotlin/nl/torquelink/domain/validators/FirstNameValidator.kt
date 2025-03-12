package nl.torquelink.domain.validators

import nl.torquelink.domain.validators.interfaces.Validator
import nl.torquelink.domain.validators.interfaces.ValidatorResult
import nl.torquelink.presentation.language.interfaces.Language

class FirstNameValidator(
    override val language: Language
) : Validator {
    override fun validate(input: CharSequence) : ValidatorResult {
        return when {
            input.isEmpty() -> ValidatorResult.Invalid(language.generic.emptyFirstNameInput)
            else -> ValidatorResult.Valid
        }
    }
}