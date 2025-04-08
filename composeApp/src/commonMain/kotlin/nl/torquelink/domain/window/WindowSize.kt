package nl.torquelink.domain.window

import androidx.compose.runtime.Composable

interface WindowSize {
    val width: Int
    val height: Int

    class Small(
        override val width: Int,
        override val height: Int
    ) : WindowSize

    class Medium(
        override val width: Int,
        override val height: Int
    ) : WindowSize

    class Large(
        override val width: Int,
        override val height: Int
    ) : WindowSize
}

@Composable
expect fun getCurrentWindowSize() : WindowSize