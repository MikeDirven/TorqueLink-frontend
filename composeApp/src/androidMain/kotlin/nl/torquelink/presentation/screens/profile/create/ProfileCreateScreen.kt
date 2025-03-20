@file:OptIn(ExperimentalMaterial3Api::class)

package nl.torquelink.presentation.screens.profile.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.toKotlinLocalDate
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage
import nl.torquelink.presentation.theme.TorqueLinkTheme
import org.jetbrains.compose.resources.painterResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.text_logo
import torquelink.composeapp.generated.resources.text_logo_dark

@Composable
fun ProfileCreateScreen(
    state: ProfileCreateScreenState,
    onEvent: (ProfileCreateScreenEvents) -> Unit,
    windowSizeClass: WindowWidthSizeClass,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    language: Language = useLanguage(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        contentWindowInsets = WindowInsets.safeContent,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        when (windowSizeClass) {
            WindowWidthSizeClass.Compact -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
                    ) {
                        Text(
                            text = language.profile.createProfileTitle,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        ProfileCreateFields(
                            firstNameValue = state.firstNameInput,
                            lastNameValue = state.lastNameInput,
                            dateOfBirthValue = state.dateOfBirthInput?.toEpochDays()
                                ?: java.time.LocalDate.now().toKotlinLocalDate().toEpochDays(),
                            phoneNumberValue = state.phoneNumberInput,
                            countryValue = state.countryInput,
                            cityValue = state.cityInput,
                            firstNameIsPrivate = state.firstNameIsPrivate,
                            lastNameIsPrivate = state.lastNameIsPrivate,
                            dateOfBirthIsPrivate = state.dateOfBirthIsPrivate,
                            phoneNumberIsPrivate = state.phoneNumberIsPrivate,
                            countryIsPrivate = state.countryIsPrivate,
                            cityIsPrivate = state.cityIsPrivate,
                            onFirstNameChange = { input, private, error ->
                                onEvent(ProfileCreateScreenEvents.FirstNameInputChanged(input, private, error))
                            },
                            onLastNameChange = { input, private, error ->
                                onEvent(ProfileCreateScreenEvents.LastNameInputChanged(input, private, error))
                            },
                            onDateOfBirthChange = { input, private, error ->
                                onEvent(ProfileCreateScreenEvents.DateOfBirthInputChanged(input, private, error))
                            },
                            onPhoneNumberChange = { input, private, error ->
                                onEvent(ProfileCreateScreenEvents.PhoneNumberInputChanged(input, private, error))
                            },
                            onCountryChange = { input, private, error ->
                                onEvent(ProfileCreateScreenEvents.CountryInputChanged(input, private, error))
                            },
                            onCityChange = { input, private, error ->
                                onEvent(ProfileCreateScreenEvents.CityInputChanged(input, private, error))
                            }
                        )

                        Button(
                            modifier = Modifier.fillMaxWidth(0.9f),
                            onClick = {
                                onEvent(ProfileCreateScreenEvents.CreateProfile)
                            },
                            enabled = !state.hasError

                        ) {
                            Text(language.profile.createProfileButton)
                        }
                    }

                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
                    ){
                        Image(
                            modifier = Modifier.padding(bottom = 10.dp),
                            painter = painterResource(
                                if(isSystemInDarkTheme())
                                    Res.drawable.text_logo_dark
                                else
                                    Res.drawable.text_logo
                            ),
                            contentDescription = null
                        )
                    }
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ){

                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_XL)
@Composable
fun RegisterScreenPreviewCompact() {
    TorqueLinkTheme {
        ProfileCreateScreen(
            state = ProfileCreateScreenState(),
            onEvent = {},
            windowSizeClass = WindowWidthSizeClass.Compact
        )
    }
}

@Preview(device = Devices.PIXEL_TABLET)
@Composable
fun RegisterScreenPreviewMedium() {
    TorqueLinkTheme {
        ProfileCreateScreen(
            state = ProfileCreateScreenState(),
            onEvent = {},
            windowSizeClass = WindowWidthSizeClass.Medium
        )
    }
}