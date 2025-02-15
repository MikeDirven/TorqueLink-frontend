package nl.torquelink.presentation.screens.reset

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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage
import nl.torquelink.presentation.theme.TorqueLinkTheme
import org.jetbrains.compose.resources.painterResource
import torquelink.composeapp.generated.resources.Res
import torquelink.composeapp.generated.resources.logo
import torquelink.composeapp.generated.resources.logo_dark
import torquelink.composeapp.generated.resources.text_logo
import torquelink.composeapp.generated.resources.text_logo_dark

@Composable
fun ResetPasswordScreen(
    state: ResetPasswordScreenState,
    onEvent: (ResetPasswordScreenEvents) -> Unit,
    windowSizeClass: WindowWidthSizeClass,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    modifier: Modifier = Modifier,
    language: Language = useLanguage(),
    resetToken: String? = null
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        contentWindowInsets = WindowInsets.safeContent,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        when(windowSizeClass){
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
                        Image(
                            painter = painterResource(
                                if(isSystemInDarkTheme())
                                    Res.drawable.logo_dark
                                else
                                    Res.drawable.logo
                            ),
                            contentDescription = null
                        )

                        ResetPasswordFields(
                            modifier = Modifier.fillMaxWidth(0.9f),
                            hasToken = !resetToken.isNullOrBlank(),
                            usernameValue = state.userInput,
                            emailValue = state.emailInput,
                            newPasswordValue = state.newPasswordInput,
                            onUsernameChange = { input, error ->
                                onEvent(ResetPasswordScreenEvents.UsernameInputChanged(input, error))
                            },
                            onEmailChange = { input, error ->
                                onEvent(ResetPasswordScreenEvents.EmailInputChanged(input, error))
                            },
                            onNewPasswordChange = { input, error ->
                                onEvent(ResetPasswordScreenEvents.NewPasswordInputChanged(input, error))
                            }
                        )

                        Button(
                            modifier = Modifier.fillMaxWidth(0.9f),
                            onClick = {
                                if(!resetToken.isNullOrBlank()){
                                    onEvent(ResetPasswordScreenEvents.OnResetPasswordPressed(resetToken))
                                } else {
                                    onEvent(ResetPasswordScreenEvents.OnResetPasswordRequestPressed)
                                }
                            },
                            enabled = !state.hasError
                        ) {
                            Text(language.resetPassword.resetPasswordButton)
                        }

                        OutlinedButton(
                            modifier = Modifier.fillMaxWidth(fraction = 0.9f),
                            onClick = {
                                onEvent(ResetPasswordScreenEvents.OnCancelPressed)
                            }
                        ) {
                            Text(language.generic.cancelButton)
                        }
                    }

                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
                    ) {
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


                            ResetPasswordFields(
                                modifier = Modifier.weight(1f),
                                hasToken = !resetToken.isNullOrBlank(),
                                usernameValue = state.userInput,
                                emailValue = state.emailInput,
                                newPasswordValue = state.newPasswordInput,
                                onUsernameChange = { input, error ->
                                    onEvent(ResetPasswordScreenEvents.UsernameInputChanged(input, error))
                                },
                                onEmailChange = { input, error ->
                                    onEvent(ResetPasswordScreenEvents.EmailInputChanged(input, error))
                                },
                                onNewPasswordChange = { input, error ->
                                    onEvent(ResetPasswordScreenEvents.NewPasswordInputChanged(input, error))
                                }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            modifier = Modifier.weight(0.5f),
                            onClick = {
                                onEvent(ResetPasswordScreenEvents.OnCancelPressed)
                            }
                        ) {
                            Text(language.generic.cancelButton)
                        }

                        Button(
                            modifier = Modifier.weight(0.5f),
                            onClick = {
                                if(!resetToken.isNullOrBlank()){
                                    onEvent(ResetPasswordScreenEvents.OnResetPasswordPressed(resetToken))
                                } else {
                                    onEvent(ResetPasswordScreenEvents.OnResetPasswordRequestPressed)
                                }
                            },
                            enabled = !state.hasError
                        ) {
                            Text(language.resetPassword.resetPasswordButton)
                        }
                    }
                }
            }
        }
    }
}

@Preview(device = Devices.PIXEL_XL)
@Composable
fun ResetPasswordScreenPreviewCompact() {
    TorqueLinkTheme {
        val viewmodel: ResetPasswordScreenViewModel = viewModel()
        ResetPasswordScreen(
            state = viewmodel.state.collectAsStateWithLifecycle().value,
            onEvent = viewmodel::dispatch,
            windowSizeClass = WindowWidthSizeClass.Compact
        )
    }
}

@Preview(device = Devices.PIXEL_TABLET)
@Composable
fun ResetPasswordScreenPreviewMedium() {
    TorqueLinkTheme {
        val viewmodel: ResetPasswordScreenViewModel = viewModel()
        ResetPasswordScreen(
            state = viewmodel.state.collectAsStateWithLifecycle().value,
            onEvent = viewmodel::dispatch,
            windowSizeClass = WindowWidthSizeClass.Medium
        )
    }
}