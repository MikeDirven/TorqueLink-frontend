package nl.torquelink.presentation.screens.profile.create

import android.net.Uri
import androidx.compose.material3.ExperimentalMaterial3Api
import kotlinx.datetime.LocalDate
import nl.torquelink.shared.enums.generic.CountryCode

@OptIn(ExperimentalMaterial3Api::class)
data class ProfileCreateScreenState(
    val hasError: Boolean = true,
    val avatarUri: Uri? = null,
    val firstNameInput: String = "",
    val firstNameIsPrivate: Boolean = false,
    val lastNameInput: String = "",
    val lastNameIsPrivate: Boolean = false,
    val dateOfBirthInput: LocalDate? = null,
    val dateOfBirthIsPrivate: Boolean = false,
    val phoneNumberInput: String = "",
    val phoneNumberIsPrivate: Boolean = false,
    val countryInput: CountryCode = CountryCode.NL,
    val countryIsPrivate: Boolean = false,
    val cityInput: String = "",
    val cityIsPrivate: Boolean = false,
)