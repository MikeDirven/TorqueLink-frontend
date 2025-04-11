package nl.torquelink.presentation.screens.group.overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.domain.window.WindowSize
import nl.torquelink.domain.window.getCurrentWindowSize
import nl.torquelink.presentation.group.overview.GroupOverviewScreenEvents
import nl.torquelink.presentation.group.overview.GroupOverviewScreenState
import nl.torquelink.presentation.screens.generic.BaseCompactScreenLayout
import nl.torquelink.presentation.screens.generic.components.AdmobBanner
import nl.torquelink.presentation.theme.TorqueLinkTheme

@Composable
fun GroupOverviewScreen(
    state: GroupOverviewScreenState,
    onEvent: (GroupOverviewScreenEvents) -> Unit,
    windowSize: WindowSize = getCurrentWindowSize(),
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    BaseCompactScreenLayout(
        modifier = modifier,
        snackBarHostState = snackBarHostState,
        activeTab = BaseScreenTabs.GROUPS,
        onTabSwitch = {
            onEvent(GroupOverviewScreenEvents.OnTabSwitch(it))
        },
        profileAvatar = state.profile?.let {
            { AsyncImage(it.avatar, "") }
        }
    ) {
        LazyColumn {
            items(state.groupsData) {
                Text(it.groupName)
            }
        }

        AdmobBanner()
    }
}

@Preview(device = Devices.PIXEL_XL)
@Composable
fun GroupOverviewScreenPreview() {
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