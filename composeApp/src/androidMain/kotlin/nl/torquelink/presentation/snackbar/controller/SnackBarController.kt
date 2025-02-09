package nl.torquelink.presentation.snackbar.controller

import kotlinx.coroutines.flow.Flow
import nl.torquelink.presentation.snackbar.model.SnackBar

interface SnackBarController {
    val snackBars: Flow<SnackBar>

    suspend fun create(
        snackBar: SnackBar,
        snackBarOptions: SnackBar.() -> Unit = {}
    )
}