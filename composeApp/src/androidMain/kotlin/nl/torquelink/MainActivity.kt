package nl.torquelink

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nl.torquelink.presentation.screens.login.LoginScreen
import nl.torquelink.presentation.screens.login.LoginScreenEvents
import nl.torquelink.presentation.screens.login.LoginScreenViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass

            App {
                val viewmodel: LoginScreenViewModel = viewModel()
                LoginScreen(
                    state = viewmodel.state.collectAsStateWithLifecycle(),
                    onEvent = viewmodel::dispatch,
                    windowSizeClass = widthSizeClass
                )
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App {

    }
}