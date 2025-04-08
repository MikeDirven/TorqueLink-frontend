package nl.torquelink.domain.window

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
actual fun getCurrentWindowSize(): WindowSize {
    val windowSizeClass = calculateWindowSizeClass(LocalActivity.current!!)

    return when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> WindowSize.Small(
            width = 0,
            height = 0
        )

        WindowWidthSizeClass.Medium -> WindowSize.Medium(
            width = 0,
            height = 0
        )

        WindowWidthSizeClass.Expanded -> WindowSize.Large(
            width = 0,
            height = 0
        )

        else -> return WindowSize.Small(
            width = 0,
            height = 0
        )
    }
}