package nl.torquelink.presentation.screens.generic.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant
import nl.torquelink.presentation.language.interfaces.Language
import nl.torquelink.presentation.language.useLanguage

@Composable
 expect fun ShowDatePicker(
    currentDate: Long,
    title: String,
    headLine: String,
    onDismiss: () -> Unit,
    onConfirm: (LocalDate) -> Unit,
    language: Language = useLanguage()
 )

@Composable
fun DatePickerField(
    selectedDate: Int,
    label: String,
    title: String,
    headLine: String,
    onConfirm: (LocalDate) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    language: Language = useLanguage()
) {
    var datePickerOpen by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = LocalDate.fromEpochDays(selectedDate).toString(),
        onValueChange = {},
        readOnly = true,
        label = { Text(label) },
        leadingIcon = {
            leadingIcon?.invoke()
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    datePickerOpen = true
                }
            ) {
                Icon(Icons.Filled.DateRange, contentDescription = "Select Date")
            }
        }
    )

    if(datePickerOpen) ShowDatePicker(
        currentDate = LocalDate.fromEpochDays(selectedDate)
            .atTime(LocalTime.fromSecondOfDay(0))
            .toInstant(
                TimeZone.currentSystemDefault()
            ).toEpochMilliseconds(),
        title = title,
        headLine = headLine,
        onDismiss = {
            datePickerOpen = false
        },
        onConfirm = {
            onConfirm(it)
            datePickerOpen = false
        },
        language = language
    )
}