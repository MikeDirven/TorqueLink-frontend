package nl.torquelink.presentation.snackbar.model


interface SnackBarOptions {
    var onActionPerformed: (() -> Unit)?
    var onDismiss: (() -> Unit)?
}

fun SnackBar.onActionPerformed(action: () -> Unit) : SnackBar {
    onActionPerformed = action
    return this
}

fun SnackBar.onDismiss(action: () -> Unit) : SnackBar {
    onDismiss = action
    return this
}