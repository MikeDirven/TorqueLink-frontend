package nl.torquelink.presentation.screens.generic.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import nl.torquelink.presentation.language.interfaces.Language
import java.time.Instant
import java.time.ZoneId

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
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = currentDate)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { millis ->
                        val instant = Instant.ofEpochMilli(millis)
                        val zoneId = ZoneId.systemDefault() // Or a specific ZoneId if needed

                        onConfirm(
                            instant.atZone(zoneId).toLocalDate().toKotlinLocalDate()
                        )
                    } ?: run {
                        val instant = Instant.now()
                        val zoneId = ZoneId.systemDefault() // Or a specific ZoneId if needed

                        onConfirm(
                            instant.atZone(zoneId).toLocalDate().toKotlinLocalDate()
                        )
                    }
                }
            ) {
                Text(language.generic.confirmButton)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(language.generic.cancelButton)
            }
        }
    ){
        DatePicker(
            state = datePickerState,
            title = { Text(title) },
            headline = { Text(headLine) },
        )
    }
}

private fun LocalDate.toJavaLocalDate(): java.time.LocalDate {
    return java.time.LocalDate.of(year, monthNumber, dayOfMonth)
}