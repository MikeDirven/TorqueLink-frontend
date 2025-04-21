package nl.torquelink.presentation.screens.timeline

import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.presentation.screens.generic.BaseCompactScreenLayout
import nl.torquelink.presentation.screens.generic.components.AdmobBanner
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
        profileAvatar = state.profile?.avatar?.let {
            { AsyncImage(
                modifier = Modifier.size(24.0.dp).clip(IconButtonDefaults.filledShape),
                model = it,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            ) }
        }
    ) {
        AdmobBanner()
    }
}

@Preview(device = Devices.PIXEL_XL)
@Composable
fun TimeLineScreenPreview() {
    TorqueLinkTheme {
        BaseCompactScreenLayout(
            snackBarHostState = remember { SnackbarHostState() },
            activeTab = BaseScreenTabs.EVENTS,
            onTabSwitch = {}
        ) {

        }
    }
}

@Preview(device = Devices.PIXEL_TABLET)
@Composable
fun TimeLineScreenTabletPreview() {
    TorqueLinkTheme {
        BaseCompactScreenLayout(
            snackBarHostState = remember { SnackbarHostState() },
            activeTab = BaseScreenTabs.EVENTS,
            onTabSwitch = {}
        ) {

        }
    }
}