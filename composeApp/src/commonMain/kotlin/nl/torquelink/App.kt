package nl.torquelink

import androidx.compose.runtime.Composable
import nl.torquelink.presentation.theme.TorqueLinkTheme

@Composable
fun App(content: @Composable () -> Unit) {


    TorqueLinkTheme {
        content()
    }
}