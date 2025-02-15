package nl.torquelink.domain.validators.interfaces

sealed interface ValidatorResult {
    data class Invalid(val message: String) : ValidatorResult

    data object Valid : ValidatorResult
}