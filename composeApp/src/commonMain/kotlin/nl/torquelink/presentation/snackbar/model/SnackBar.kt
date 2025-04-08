package nl.torquelink.presentation.snackbar.model

import androidx.compose.material3.SnackbarDuration

data class SnackBar(
    val message: String,
    val actionLabel: String? = null,
    val duration: SnackbarDuration = SnackbarDuration.Short
) : SnackBarOptions {
    override var onActionPerformed: (() -> Unit)? = null
    override var onDismiss: (() -> Unit)? = null
}