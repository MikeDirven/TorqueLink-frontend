package nl.torquelink.presentation.screens.profile.create

import android.net.Uri
import nl.torquelink.shared.enums.CountryCode
import java.time.LocalDate

data class ProfileCreateScreenState(
    val avatarUri: Uri? = null,
    val firstNameInput: String = "",
    val lastNameInput: String = "",
    val dateOfBirthInput: LocalDate? = null,
    val phoneNumberInput: String = "",
    val countryInput: CountryCode = CountryCode.NL,
    val cityInput: String = ""
)
