package nl.torquelink.presentation.screens.profile.create

import kotlinx.datetime.LocalDate
import nl.torquelink.shared.enums.generic.CountryCode

sealed interface ProfileCreateScreenEvents {
    data class FirstNameInputChanged(
        val input: String,
        val private: Boolean,
        val error: Boolean = false
    ) : ProfileCreateScreenEvents

    data class LastNameInputChanged(
        val input: String,
        val private: Boolean,
        val error: Boolean = false
    ) : ProfileCreateScreenEvents

    data class DateOfBirthInputChanged(
        val input: LocalDate,
        val private: Boolean,
        val error: Boolean = false
    ) : ProfileCreateScreenEvents

    data class PhoneNumberInputChanged(
        val input: String,
        val private: Boolean,
        val error: Boolean = false
    ) : ProfileCreateScreenEvents

    data class CountryInputChanged(
        val input: CountryCode,
        val private: Boolean,
        val error: Boolean = false
    ) : ProfileCreateScreenEvents

    data class CityInputChanged(
        val input: String,
        val private: Boolean,
        val error: Boolean = false
    ) : ProfileCreateScreenEvents

    data object CreateProfile : ProfileCreateScreenEvents
}