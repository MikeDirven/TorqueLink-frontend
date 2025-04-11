package nl.torquelink.presentation.screens.reset.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import nl.torquelink.domain.validators.EmailAddressValidator
import nl.torquelink.domain.validators.PasswordValidator
import nl.torquelink.domain.validators.UsernameValidator
import nl.torquelink.domain.validators.interfaces.onInvalid
import nl.torquelink.domain.validators.interfaces.onValid
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage

@Composable
fun ResetPasswordFields(
    modifier: Modifier = Modifier,
    hasToken: Boolean,
    usernameValue: String,
    emailValue: String,
    newPasswordValue: String,
    onUsernameChange: (input: String, error: Boolean) -> Unit,
    onEmailChange: (input: String, error: Boolean) -> Unit,
    onNewPasswordChange: (input: String, error: Boolean) -> Unit,
    language: Language = useLanguage()
) {
    var userInputException by remember { mutableStateOf<String?>(null) }
    var emailInputException by remember { mutableStateOf<String?>(null) }
    var newPasswordInputException by remember { mutableStateOf<String?>(null) }
    val hasErrors = when {
        !hasToken && userInputException != null -> true
        !hasToken && emailInputException != null -> true
        hasToken && newPasswordInputException != null -> true
        else -> null
    }
    val isEmpty = when {
        !hasToken && usernameValue.isEmpty() -> true
        !hasToken && emailValue.isEmpty() -> true
        hasToken && newPasswordValue.isEmpty() -> true
        else -> null
    }

    fun checkUsername(input: String) {
        UsernameValidator(language).validate(input)
            .onInvalid { exception ->
                userInputException = exception
                onUsernameChange(input, hasErrors ?: isEmpty ?: true)
            }.onValid {
                userInputException = null
                onUsernameChange(input, hasErrors ?: isEmpty ?: false)
            }
    }

    fun checkEmail(input: String) {
        EmailAddressValidator(language).validate(input)
            .onInvalid { exception ->
                emailInputException = exception
                onEmailChange(input, hasErrors ?: isEmpty ?: true)
            }.onValid {
                emailInputException = null
                onEmailChange(input, hasErrors ?: isEmpty ?: false)
            }
    }

    fun checkNewPassword(input: String) {
        PasswordValidator(language).validate(input)
            .onInvalid { exception ->
                newPasswordInputException = exception
                onNewPasswordChange(input, hasErrors ?: isEmpty ?: true)
            }.onValid {
                newPasswordInputException = null
                onNewPasswordChange(input, hasErrors ?: isEmpty ?: false)
            }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        if(!hasToken) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                isError = !userInputException.isNullOrBlank(),
                supportingText = {
                    if (!userInputException.isNullOrBlank()) {
                        Text(userInputException!!)
                    }
                },
                singleLine = true,
                value = usernameValue,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                onValueChange = {
                    checkUsername(it)
                },
                label = { Text(language.generic.userFieldLabel) }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                isError = !emailInputException.isNullOrBlank(),
                supportingText = {
                    if (!emailInputException.isNullOrBlank()) {
                        Text(emailInputException!!)
                    }
                },
                singleLine = true,
                value = emailValue,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                onValueChange = {
                    checkEmail(it)
                },
                label = { Text(language.generic.emailFieldLabel) }
            )
        } else {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                isError = !newPasswordInputException.isNullOrBlank(),
                supportingText = {
                    if(!newPasswordInputException.isNullOrBlank()) {
                        Text(newPasswordInputException!!)
                    }
                },
                singleLine = true,
                value = newPasswordValue,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                onValueChange = {
                    checkNewPassword(it)
                },
                label = { Text(language.generic.passwordFieldLabel) }
            )
        }
    }
}