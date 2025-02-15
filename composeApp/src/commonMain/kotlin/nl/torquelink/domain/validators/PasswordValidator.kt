package nl.torquelink.domain.validators

import androidx.compose.runtime.Composable
import nl.torquelink.domain.validators.interfaces.Validator
import nl.torquelink.domain.validators.interfaces.ValidatorResult
import nl.torquelink.presentation.language.interfaces.Language

class PasswordValidator(
    override val language: Language
) : Validator {
    private val lowerCaseRegex = Regex(".*[a-z].*")
    private val upperCaseRegex = Regex(".*[A-Z].*")
    private val specialRegex = Regex(".*[^a-zA-Z\\\\d\\\\s].*")

    override fun validate(input: CharSequence) : ValidatorResult {
        return when {
            input.isEmpty() -> ValidatorResult.Invalid(language.generic.emptyPasswordInput)
            input.length < 8 -> ValidatorResult.Invalid(language.generic.passwordToShort)
            !lowerCaseRegex.matches(input) -> ValidatorResult.Invalid(language.generic.passwordNeedsLowercaseCharacter)
            !upperCaseRegex.matches(input) -> ValidatorResult.Invalid(language.generic.passwordNeedsUppercaseCharacter)
            !specialRegex.matches(input) -> ValidatorResult.Invalid(language.generic.passwordNeedsSpecialCharacter)
            else -> ValidatorResult.Valid
        }
    }
}