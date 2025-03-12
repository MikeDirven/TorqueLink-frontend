package nl.torquelink.presentation.screens.generic.components

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import nl.torquelink.presentation.language.interfaces.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun ShowDatePicker(
    currentDate: Long,
    title: String,
    headLine: String,
    onDismiss: () -> Unit,
    onConfirm: (LocalDate) -> Unit,
    language: Language
) {

}