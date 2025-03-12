package nl.torquelink.presentation.screens.profile.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import nl.torquelink.domain.validators.CityValidator
import nl.torquelink.domain.validators.FirstNameValidator
import nl.torquelink.domain.validators.LastNameValidator
import nl.torquelink.domain.validators.PhoneNumberValidator
import nl.torquelink.domain.validators.interfaces.onInvalid
import nl.torquelink.domain.validators.interfaces.onValid
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage
import nl.torquelink.presentation.screens.generic.components.DatePickerField
import nl.torquelink.presentation.screens.generic.components.SelectField
import nl.torquelink.shared.enums.generic.CountryCode
import org.jetbrains.compose.resources.painterResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.visible
import torquelink.composeapp.generated.resources.visible_off

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCreateFields(
    modifier: Modifier = Modifier,
    firstNameValue: String,
    firstNameIsPrivate: Boolean,
    lastNameValue: String,
    lastNameIsPrivate: Boolean,
    dateOfBirthValue: Int,
    dateOfBirthIsPrivate: Boolean,
    phoneNumberValue: String,
    phoneNumberIsPrivate: Boolean,
    countryValue: CountryCode,
    countryIsPrivate: Boolean,
    cityValue: String,
    cityIsPrivate: Boolean,
    onFirstNameChange: (input: String, private: Boolean, error: Boolean) -> Unit,
    onLastNameChange: (input: String, private: Boolean, error: Boolean) -> Unit,
    onDateOfBirthChange: (input: LocalDate, private: Boolean, error: Boolean) -> Unit,
    onPhoneNumberChange: (input: String, private: Boolean, error: Boolean) -> Unit,
    onCountryChange: (input: CountryCode, private: Boolean, error: Boolean) -> Unit,
    onCityChange: (input: String, private: Boolean, error: Boolean) -> Unit,
    language: Language = useLanguage()
) {
    var firstNameInputException by remember { mutableStateOf<String?>(null) }
    var lastNameInputException by remember { mutableStateOf<String?>(null) }
    var phoneNumberInputException by remember { mutableStateOf<String?>(null) }
    var countryInputException by remember { mutableStateOf<String?>(null) }
    var cityInputException by remember { mutableStateOf<String?>(null) }

    var hasErrors = when {
        firstNameInputException != null -> true
        lastNameInputException != null -> true
        phoneNumberInputException != null -> true
        countryInputException != null -> true
        cityInputException != null -> true
        else -> null
    }
    val isEmpty = when {
        firstNameValue.isEmpty() -> true
        lastNameValue.isEmpty() -> true
        phoneNumberValue.isEmpty() -> true
        cityValue.isEmpty() -> true
        else -> null
    }

    fun checkFirstName(input: String) {
        FirstNameValidator(language).validate(input)
            .onInvalid { exception ->
                firstNameInputException = exception
                onFirstNameChange(input, firstNameIsPrivate, hasErrors ?: isEmpty ?: true)
            }.onValid {
                firstNameInputException = null
                onFirstNameChange(input, firstNameIsPrivate, hasErrors ?: isEmpty ?: false)
            }
    }

    fun checkLastName(input: String) {
        LastNameValidator(language).validate(input)
            .onInvalid { exception ->
                lastNameInputException = exception
                onLastNameChange(input, lastNameIsPrivate, hasErrors ?: isEmpty ?: true)
            }.onValid {
                lastNameInputException = null
                onLastNameChange(input, lastNameIsPrivate, hasErrors ?: isEmpty ?: false)
            }
    }

    fun checkPhoneNumber(input: String) {
        PhoneNumberValidator(language).validate(input)
            .onInvalid { exception ->
                phoneNumberInputException = exception
                onPhoneNumberChange(input, phoneNumberIsPrivate, hasErrors ?: isEmpty ?: true)
            }.onValid {
                phoneNumberInputException = null
                onPhoneNumberChange(input, phoneNumberIsPrivate, hasErrors ?: isEmpty ?: false)
            }
    }

    fun checkCity(input: String) {
        CityValidator(language).validate(input)
            .onInvalid { exception ->
                cityInputException = exception
                onCityChange(input, cityIsPrivate, hasErrors ?: isEmpty ?: true)
            }.onValid {
                cityInputException = null
                onCityChange(input, cityIsPrivate, hasErrors ?: isEmpty ?: false)
            }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                isError = !firstNameInputException.isNullOrBlank(),
                supportingText = {
                    if(!firstNameInputException.isNullOrBlank()){
                        Text(firstNameInputException!!)
                    }
                },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            onFirstNameChange(firstNameValue, !firstNameIsPrivate, hasErrors ?: isEmpty ?: false)
                        }
                    ) {
                        when(firstNameIsPrivate) {
                            true -> Icon(painterResource(Res.drawable.visible_off), "Private")
                            false -> Icon(painterResource(Res.drawable.visible), "Public")
                        }
                    }
                },
                singleLine = true,
                value = firstNameValue,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onValueChange = {
                    checkFirstName(it)
                },
                label = { Text(language.profile.firstNameFieldLabel) }
            )
        }


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            isError = !lastNameInputException.isNullOrBlank(),
            supportingText = {
                if(!lastNameInputException.isNullOrBlank()){
                    Text(lastNameInputException!!)
                }
            },
            singleLine = true,
            value = lastNameValue,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = {
                checkLastName(it)
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        onLastNameChange(lastNameValue, !lastNameIsPrivate, hasErrors ?: isEmpty ?: false)
                    }
                ) {
                    when(lastNameIsPrivate) {
                        true -> Icon(painterResource(Res.drawable.visible_off), "Private")
                        false -> Icon(painterResource(Res.drawable.visible), "Public")
                    }
                }
            },
            label = { Text(language.profile.lastNameFieldLabel) }
        )

        DatePickerField(
            selectedDate = dateOfBirthValue,
            label = language.profile.dateOfBirthFieldLabel,
            title = language.profile.dateOfBirthFieldLabel,
            headLine = language.profile.dateOfBirthFieldLabel,
            onConfirm = {
                onDateOfBirthChange(it, dateOfBirthIsPrivate, false)
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        onDateOfBirthChange(
                            LocalDate.fromEpochDays(dateOfBirthValue),
                            !dateOfBirthIsPrivate,
                            hasErrors ?: isEmpty ?: false
                        )
                    }
                ) {
                    when(dateOfBirthIsPrivate) {
                        true -> Icon(painterResource(Res.drawable.visible_off), "Private")
                        false -> Icon(painterResource(Res.drawable.visible), "Public")
                    }
                }
            },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            isError = !phoneNumberInputException.isNullOrBlank(),
            supportingText = {
                if(!phoneNumberInputException.isNullOrBlank()){
                    Text(phoneNumberInputException!!)
                }
            },
            singleLine = true,
            value = phoneNumberValue,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = {
                checkPhoneNumber(it)
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        onPhoneNumberChange(phoneNumberValue, !phoneNumberIsPrivate, hasErrors ?: isEmpty ?: false)
                    }
                ) {
                    when(phoneNumberIsPrivate) {
                        true -> Icon(painterResource(Res.drawable.visible_off), "Private")
                        false -> Icon(painterResource(Res.drawable.visible), "Public")
                    }
                }
            },
            label = { Text(language.profile.phoneNumberFieldLabel) }
        )

        SelectField (
            options = CountryCode.entries,
            label = language.profile.countryFieldLabel,
            selectedOption = countryValue,
            valueFormater = {
                it.country
            },
            onOptionSelected = {
                onCountryChange(it, countryIsPrivate, hasErrors ?: isEmpty ?: false)
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        onCountryChange(countryValue, !countryIsPrivate, hasErrors ?: isEmpty ?: false)
                    }
                ) {
                    when(countryIsPrivate) {
                        true -> Icon(painterResource(Res.drawable.visible_off), "Private")
                        false -> Icon(painterResource(Res.drawable.visible), "Public")
                    }
                }
            },
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            isError = cityValue.isNotBlank(),
            supportingText = {
                if(!cityInputException.isNullOrBlank()){
                    Text(cityInputException!!)
                }
            },
            singleLine = true,
            value = cityValue,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onValueChange = {
                checkCity(it)
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        onCityChange(cityValue, !cityIsPrivate, hasErrors ?: isEmpty ?: false)
                    }
                ) {
                    when(cityIsPrivate) {
                        true -> Icon(painterResource(Res.drawable.visible_off), "Private")
                        false -> Icon(painterResource(Res.drawable.visible), "Public")
                    }
                }
            },
            label = { Text(language.profile.cityFieldLabel) }
        )


    }
}