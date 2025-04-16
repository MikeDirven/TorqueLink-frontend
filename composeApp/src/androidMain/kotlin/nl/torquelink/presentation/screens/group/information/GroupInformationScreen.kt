package nl.torquelink.presentation.screens.group.information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.domain.window.WindowSize
import nl.torquelink.domain.window.getCurrentWindowSize
import nl.torquelink.presentation.screens.generic.BaseCompactScreenLayout
import nl.torquelink.presentation.screens.generic.components.AdmobBanner
import nl.torquelink.presentation.screens.group.components.GroupListItem
import nl.torquelink.presentation.screens.group.overview.GroupOverviewScreenEvents
import nl.torquelink.presentation.screens.group.overview.GroupOverviewScreenState
import nl.torquelink.presentation.theme.TorqueLinkTheme
import nl.torquelink.shared.enums.group.MemberListVisibility
import nl.torquelink.shared.models.group.Groups

@Composable
fun GroupInformationScreen(
    state: GroupInformationScreenState,
    onEvent: (GroupInformationScreenEvents) -> Unit,
    windowSize: WindowSize = getCurrentWindowSize(),
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    BaseCompactScreenLayout(
        modifier = modifier,
        snackBarHostState = snackBarHostState,
        activeTab = BaseScreenTabs.GROUPS,
        onTabSwitch = {
            onEvent(GroupInformationScreenEvents.OnTabSwitch(it))
        },
        profileAvatar = state.profile?.let {
            { AsyncImage(it.avatar, "") }
        }
    ) {
        when(state) {
            is GroupInformationScreenState.ErrorScreenState -> {

            }
            is GroupInformationScreenState.LoadingScreenState -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                    )
                }
            }
            is GroupInformationScreenState.ScreenStateWithData -> {

            }
        }
    }
}

@Preview(device = Devices.PIXEL_XL)
@Composable
fun GroupOverviewScreen() {
    TorqueLinkTheme {
        GroupInformationScreen(
            windowSize = WindowSize.Small(0,0),
            state = GroupInformationScreenState.LoadingScreenState(null),
            onEvent = {},
            snackBarHostState = remember { SnackbarHostState() }
        )
    }
}

@Preview(device = Devices.PIXEL_TABLET)
@Composable
fun TimeLineScreenTabletPreview() {
    TorqueLinkTheme {
        GroupInformationScreen(
            windowSize = WindowSize.Small(0,0),
            state = GroupInformationScreenState.LoadingScreenState(null),
            onEvent = {},
            snackBarHostState = remember { SnackbarHostState() }
        )
    }
}