package nl.torquelink.presentation.screens.register.components

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
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage

@Composable
fun RegisterFields(
    modifier: Modifier = Modifier,
    usernameValue: String,
    passwordValue: String,
    emailValue: String,
    onUsernameChange: (input: String, error: Boolean) -> Unit,
    onPasswordChange: (input: String, error: Boolean) -> Unit,
    onEmailChange: (input: String, error: Boolean) -> Unit,
    language: Language = useLanguage()
) {
    var userInputException by remember { mutableStateOf<String?>(null) }
    var passwordInputException by remember { mutableStateOf<String?>(null) }
    var emailInputException by remember { mutableStateOf<String?>(null) }

    fun checkUsername(input: String) {
        val userInputCheck = input.isBlank()
        if(userInputCheck) {
            userInputException = language.generic.emptyUserInput
        }

        onUsernameChange(input, userInputCheck)
    }

    fun checkPassword(input: String) {
        val passwordInputCheck = input.isBlank()
        if(passwordInputCheck) {
            passwordInputException = language.generic.emptyPasswordInput
        }

        onPasswordChange(input, passwordInputCheck)
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        val pattern = Regex(emailRegex)
        val matcher = pattern.matchEntire(email)
        return matcher != null
    }

    fun checkEmail(input: String) {
        val emailInputCheck = input.isBlank()
        val emailValid = isValidEmail(input)
        if(emailInputCheck) {
            emailInputException = language.generic.emptyEmailInput
        }
        if(!emailValid) {
            emailInputException = language.generic.emailNotValid
        }

        onEmailChange(input, emailInputCheck || emailValid)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            isError = !userInputException.isNullOrBlank(),
            supportingText = {
                if(!userInputException.isNullOrBlank()){
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
            isError = !passwordInputException.isNullOrBlank(),
            supportingText = {
                if(!passwordInputException.isNullOrBlank()) {
                    Text(passwordInputException!!)
                }
            },
            singleLine = true,
            value = passwordValue,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            onValueChange = {
                checkPassword(it)
            },
            label = { Text(language.generic.passwordFieldLabel) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            isError = !emailInputException.isNullOrBlank(),
            supportingText = {
                if(!emailInputException.isNullOrBlank()) {
                    Text(emailInputException!!)
                }
            },
            singleLine = true,
            value = emailValue,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            onValueChange = {
                checkEmail(it)
            },
            label = { Text(language.generic.emailFieldLabel) }
        )
    }
}