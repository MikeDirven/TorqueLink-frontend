package nl.torquelink.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.torquelink.domain.window.WindowSize
import nl.torquelink.domain.window.getCurrentWindowSize
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage
import nl.torquelink.presentation.screens.login.components.LoginFields
import nl.torquelink.presentation.theme.TorqueLinkTheme
import org.jetbrains.compose.resources.painterResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.logo
import torquelink.composeapp.generated.resources.logo_dark
import torquelink.composeapp.generated.resources.text_logo
import torquelink.composeapp.generated.resources.text_logo_dark

@Composable
fun LoginScreen(
    state: LoginScreenState,
    onEvent: (LoginScreenEvents) -> Unit,
    windowSize: WindowSize = getCurrentWindowSize(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    modifier: Modifier = Modifier,
    language: Language = useLanguage()
) {
    LaunchedEffect(Unit) {
        onEvent(LoginScreenEvents.TryLoginWithRememberToken)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        contentWindowInsets = WindowInsets.safeContent,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->

        when(windowSize){
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
                        Image(
                            painter = painterResource(
                                if(isSystemInDarkTheme())
                                    Res.drawable.logo_dark
                                else
                                    Res.drawable.logo
                            ),
                            contentDescription = null
                        )

                        LoginFields(
                            modifier = Modifier.fillMaxWidth(0.9f),
                            usernameValue = state.userInput,
                            passwordValue = state.passwordInput,
                            onUsernameChange = { input, error ->
                                onEvent(LoginScreenEvents.UsernameInputChanged(input, error))
                            },
                            onPasswordChange = { input, error ->
                                onEvent(LoginScreenEvents.PasswordInputChanged(input, error))
                            }
                        )

                        Button(
                            modifier = Modifier.fillMaxWidth(0.9f),
                            onClick = {
                                onEvent(LoginScreenEvents.OnLoginButtonPressed)
                            },
                            enabled = !state.hasError
                        ) {
                            Text(language.login.loginButton)
                        }

                        TextButton(
                            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
                            onClick = {
                                onEvent(LoginScreenEvents.OnForgotPasswordPressed)
                            }
                        ) {
                            Text(language.login.forgottenPasswordButtons)
                        }
                    }

                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
                    ) {
                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
                            onClick = {
                                onEvent(LoginScreenEvents.OnCreateAccountPressed)
                            }
                        ) {
                            Text(language.login.createAccountButton)
                        }

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
                ) {
                    Column(
                        modifier = Modifier.padding(top = 10.dp).fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp).fillMaxHeight(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
                            ) {
                                Image(
                                    modifier = Modifier.fillMaxSize(0.3f),
                                    painter = painterResource(
                                        if(isSystemInDarkTheme())
                                            Res.drawable.logo_dark
                                        else
                                            Res.drawable.logo
                                    ),
                                    contentDescription = null
                                )

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


                            LoginFields(
                                modifier = Modifier.weight(1f),
                                usernameValue = state.userInput,
                                passwordValue = state.passwordInput,
                                onUsernameChange = { input, error ->
                                    onEvent(LoginScreenEvents.UsernameInputChanged(input, error))
                                },
                                onPasswordChange = { input, error ->
                                    onEvent(LoginScreenEvents.PasswordInputChanged(input, error))
                                }
                            )
                        }
                    }


                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top)
                    ) {
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                modifier = Modifier.weight(0.5f),
                                onClick = {
                                    onEvent(LoginScreenEvents.OnCreateAccountPressed)
                                }
                            ) {
                                Text(language.login.createAccountButton)
                            }

                            Button(
                                modifier = Modifier.weight(0.5f),
                                onClick = {},
                                enabled = !state.hasError
                            ) {
                                Text(language.login.loginButton)
                            }
                        }

                        TextButton(
                            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
                            onClick = {
                                onEvent(LoginScreenEvents.OnForgotPasswordPressed)
                            }
                        ) {
                            Text(language.login.forgottenPasswordButtons)
                        }
                    }
                }
            }
        }
    }
}