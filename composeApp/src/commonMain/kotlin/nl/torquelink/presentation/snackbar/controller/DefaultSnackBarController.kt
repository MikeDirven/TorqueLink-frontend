package nl.torquelink.presentation.snackbar.controller

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import nl.torquelink.presentation.snackbar.model.SnackBar

object DefaultSnackBarController : SnackBarController {
    private val _snackBars = Channel<SnackBar>()
    override val snackBars = _snackBars.receiveAsFlow()

    override suspend fun create(
        snackBar: SnackBar,
        snackBarOptions: SnackBar.() -> Unit
    ) {
        _snackBars.send(
            snackBar.apply(snackBarOptions)
        )
    }
}