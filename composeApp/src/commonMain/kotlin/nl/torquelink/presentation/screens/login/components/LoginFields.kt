package nl.torquelink.presentation.screens.login.components

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
import nl.torquelink.presentation.language.useLanguage

@Composable
fun LoginFields(
    modifier: Modifier = Modifier,
    usernameValue: String,
    passwordValue: String,
    onUsernameChange: (input: String, error: Boolean) -> Unit,
    onPasswordChange: (input: String, error: Boolean) -> Unit
) {
    val language = useLanguage()
    var userInputException by remember { mutableStateOf<String?>(null) }
    var passwordInputException by remember { mutableStateOf<String?>(null) }

    fun checkUsername(input: String) {
        val userInputCheck = input.isBlank()
        if(userInputCheck) {
            userInputException = language.loginScreen.emptyUserInput
        }

        onUsernameChange(input, userInputCheck)
    }

    fun checkPassword(input: String) {
        val passwordInputCheck = input.isBlank()
        if(passwordInputCheck) {
            passwordInputException = language.loginScreen.emptyPasswordInput
        }

        onPasswordChange(input, passwordInputCheck)
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
            label = { Text(language.loginScreen.userFieldLabel) }
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
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            onValueChange = {
                checkPassword(it)
            },
            label = { Text(language.loginScreen.passwordFieldLabel) }
        )
    }
}