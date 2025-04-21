package nl.torquelink.domain.window

import androidx.compose.runtime.Composable

@Composable
actual fun getCurrentWindowSize() : WindowSize {
    return WindowSize.Small(0,0)
}