package nl.torquelink.presentation.screens.profile.create

import nl.torquelink.shared.enums.generic.CountryCode
import java.time.LocalDate

sealed interface ProfileCreateScreenEvents {
    data class FirstNameInputChanged(val input: String, val error: Boolean = false) : ProfileCreateScreenEvents
    data class LastNameInputChanged(val input: String, val error: Boolean = false) : ProfileCreateScreenEvents
    data class DateOfBirthInputChanged(val input: LocalDate, val error: Boolean = false) : ProfileCreateScreenEvents
    data class PhoneNumberInputChanged(val input: String, val error: Boolean = false) : ProfileCreateScreenEvents
    data class CountryInputChanged(val input: CountryCode, val error: Boolean = false) : ProfileCreateScreenEvents
    data class CityInputChanged(val input: String, val error: Boolean = false) : ProfileCreateScreenEvents
}