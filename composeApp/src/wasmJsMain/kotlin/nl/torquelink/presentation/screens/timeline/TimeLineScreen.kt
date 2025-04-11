package nl.torquelink.presentation.screens.timeline

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.presentation.screens.generic.BaseCompactScreenLayout
import nl.torquelink.presentation.theme.TorqueLinkTheme

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
        profileAvatar = state.profile?.let {
            { AsyncImage(it.avatar, "") }
        }
    ) {

    }
}