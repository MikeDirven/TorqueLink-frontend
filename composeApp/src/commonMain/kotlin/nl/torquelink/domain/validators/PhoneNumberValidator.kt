package nl.torquelink.domain.validators

import nl.torquelink.domain.validators.interfaces.Validator
import nl.torquelink.domain.validators.interfaces.ValidatorResult
import nl.torquelink.presentation.language.interfaces.Language

class PhoneNumberValidator(
    override val language: Language
) : Validator {
    private val emailRegex = Regex("^(\\+\\d{1,3}[- ]?)?(\\(\\d{3}\\)[- ]?|\\d{3}[- ]?)?\\d{3}[- ]?\\d{4,7}\$")

    override fun validate(input: CharSequence) : ValidatorResult {
        return when {
            emailRegex.matches(input) -> ValidatorResult.Valid
            input.isBlank() -> ValidatorResult.Invalid(language.generic.emptyPhoneNumberInput)
            else -> ValidatorResult.Invalid(language.generic.phoneNumberNotValid)
        }
    }
}