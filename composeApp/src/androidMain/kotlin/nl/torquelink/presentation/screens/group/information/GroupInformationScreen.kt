package nl.torquelink.presentation.screens.group.information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.domain.window.WindowSize
import nl.torquelink.domain.window.getCurrentWindowSize
import nl.torquelink.presentation.screens.generic.BaseCompactScreenLayout
import nl.torquelink.presentation.screens.generic.components.LoadingIndicator
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
        profileAvatar = state.profile?.avatar?.let {
            { AsyncImage(
                modifier = Modifier.size(24.0.dp).clip(IconButtonDefaults.filledShape),
                model = it,
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            ) }
        }
    ) {
        when(state) {
            is GroupInformationScreenState.ErrorScreenState -> {

            }
            is GroupInformationScreenState.LoadingScreenState -> {
                LoadingIndicator()
            }
            is GroupInformationScreenState.ScreenStateWithData -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    AsyncImage(
                        modifier = Modifier.height(120.dp).fillMaxWidth(),
                        model = state.groupData.coverPhotoUrl,
                        contentDescription = null,
                    )

                    OutlinedCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = state.groupData.groupName,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                }
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
            state = STATE_WITH_GROUP_DATA,
            onEvent = {},
            snackBarHostState = remember { SnackbarHostState() }
        )
    }
}

@Preview(device = Devices.PIXEL_TABLET)
@Composable
fun GroupInformationScreenTabletPreview() {
    TorqueLinkTheme {
        GroupInformationScreen(
            windowSize = WindowSize.Small(0,0),
            state = STATE_WITH_GROUP_DATA,
            onEvent = {},
            snackBarHostState = remember { SnackbarHostState() }
        )
    }
}

val STATE_WITH_GROUP_DATA = GroupInformationScreenState.ScreenStateWithData(
    profile = null,
    groupData = Groups.GroupWithDetailsDto(
        id = 23434,
        members = emptyList(),
        events = emptyList(),
        groupName = "Torquelink",
        description = "Test description",
        logoUrl = "https://autonxt.net/wp-content/uploads/2018/04/autocontentexp.com370z-drift_1200-f7d42d39ce1d4fb0551fc0a947576f88240181ad.jpg",
        coverPhotoUrl = "https://www.completecovergroup.com/wp-content/uploads/2019/01/homepage-banner-license-plate-3-compressed.jpg",
        privateGroup = false,
        joinRequestsEnabled = true,
        memberListVisibility = MemberListVisibility.VISIBLE,
        facebookUrl = "",
        instagramUrl = "",
        twitterUrl = "",
        linkedInUrl = "",
        websiteUrl = ""
    )
)