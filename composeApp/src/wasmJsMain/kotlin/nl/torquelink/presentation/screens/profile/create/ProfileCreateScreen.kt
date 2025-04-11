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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.datetime.internal.JSJoda.LocalDate
import kotlinx.datetime.internal.JSJoda.ZoneId
import nl.torquelink.domain.window.WindowSize
import nl.torquelink.domain.window.getCurrentWindowSize
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage
import org.jetbrains.compose.resources.painterResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.text_logo
import torquelink.composeapp.generated.resources.text_logo_dark

@Composable
fun ProfileCreateScreen(
    state: ProfileCreateScreenState,
    onEvent: (ProfileCreateScreenEvents) -> Unit,
    windowSize: WindowSize = getCurrentWindowSize(),
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
        when (windowSize) {
            is WindowSize.Small -> {
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
                                ?: LocalDate.now(ZoneId.UTC).toEpochDay().toInt(),
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