package nl.torquelink

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpace
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import nl.torquelink.presentation.TorqueLinkApp
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            val deepLinkIntent = intent

            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    MaterialTheme.colorScheme.primary.toArgb(),
                    MaterialTheme.colorScheme.primary.toArgb()
                ),
            )

            App {
                KoinContext {
                    TorqueLinkApp(
                        windowSizeClass = widthSizeClass,
                        deepLinkIntent = deepLinkIntent
                    )
                }
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