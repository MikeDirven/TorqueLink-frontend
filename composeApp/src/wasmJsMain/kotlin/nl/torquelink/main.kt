package nl.torquelink

import androidx.compose.material.Text
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import nl.torquelink.data.di.CommonDataModule
import nl.torquelink.data.di.DataModule
import nl.torquelink.presentation.TorqueLinkApp
import nl.torquelink.presentation.di.PresentationModule
import org.koin.compose.KoinApplication

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        KoinApplication(
            application = {
                modules(
                    DataModule,
                    PresentationModule
                )
            },
            content = {
                App {
                    TorqueLinkApp()
                }
            }
        )
    }
}