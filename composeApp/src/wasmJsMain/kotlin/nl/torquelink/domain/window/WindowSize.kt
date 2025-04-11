package nl.torquelink.domain.window

import androidx.compose.runtime.Composable
import kotlinx.browser.window

@Composable
actual fun getCurrentWindowSize(): WindowSize {
    val width = window.innerWidth
    val height = window.innerHeight

    return if (width < 600) {
        WindowSize.Small(width, height)
    } else if (width < 900) {
        WindowSize.Medium(width, height)
    } else {
        WindowSize.Large(width, height)
    }
}