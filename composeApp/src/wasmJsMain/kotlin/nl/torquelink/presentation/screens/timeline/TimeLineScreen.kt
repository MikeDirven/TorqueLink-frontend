package nl.torquelink.presentation.screens.timeline

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.presentation.screens.generic.BaseCompactScreenLayout

@Composable
fun TimeLineScreen(
    state: TimeLineScreenState,
    onEvent: (TimeLineScreenEvents) -> Unit,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    BaseCompactScreenLayout(
        modifier = modifier,
        snackBarHostState = snackBarHostState,
        activeTab = BaseScreenTabs.HOME,
        onTabSwitch = {
            onEvent(TimeLineScreenEvents.OnTabSwitch(it))
        },
        profileAvatar = state.profile?.avatar?.let {
            { AsyncImage(
                modifier = Modifier.size(24.0.dp).clip(IconButtonDefaults.filledShape),
                model = it,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            ) }
        }
    ) {

    }
}