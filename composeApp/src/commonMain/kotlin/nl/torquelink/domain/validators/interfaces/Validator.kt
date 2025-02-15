package nl.torquelink.domain.validators.interfaces

import androidx.compose.runtime.Composable
import nl.torquelink.presentation.language.interfaces.Language

interface Validator {
    val language: Language

    abstract fun validate(input: CharSequence) : ValidatorResult
}

fun ValidatorResult.onInvalid(block: (String) -> Unit) : ValidatorResult {
    if(this is ValidatorResult.Invalid) block(message)
    return this
}

fun ValidatorResult.onValid(block: () -> Unit) : ValidatorResult {
    if(this is ValidatorResult.Valid) block()
    return this
}