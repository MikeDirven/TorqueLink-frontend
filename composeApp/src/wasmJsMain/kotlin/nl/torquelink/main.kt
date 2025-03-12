package nl.torquelink

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    koinApplication {
        modules()

        ComposeViewport(document.body!!) {
            App {

            }
        }
    }
}