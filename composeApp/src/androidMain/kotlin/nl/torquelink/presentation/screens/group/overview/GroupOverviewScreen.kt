package nl.torquelink.presentation.screens.group.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SnackbarHostState
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
import nl.torquelink.domain.Pagination
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.domain.window.WindowSize
import nl.torquelink.domain.window.getCurrentWindowSize
import nl.torquelink.presentation.screens.generic.BaseCompactScreenLayout
import nl.torquelink.presentation.screens.generic.components.AdmobBanner
import nl.torquelink.presentation.screens.generic.components.LoadingIndicator
import nl.torquelink.presentation.screens.generic.components.PaginatedLazyColum
import nl.torquelink.presentation.screens.group.components.GroupListItem
import nl.torquelink.presentation.theme.TorqueLinkTheme
import nl.torquelink.shared.enums.group.MemberListVisibility
import nl.torquelink.shared.models.group.Groups

@Composable
fun GroupOverviewScreen(
    state: GroupOverviewScreenState,
    onEvent: (GroupOverviewScreenEvents) -> Unit,
    windowSize: WindowSize = getCurrentWindowSize(),
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    when(windowSize){
        is WindowSize.Small -> {
            BaseCompactScreenLayout(
                modifier = modifier,
                snackBarHostState = snackBarHostState,
                activeTab = BaseScreenTabs.GROUPS,
                onTabSwitch = {
                    onEvent(GroupOverviewScreenEvents.OnTabSwitch(it))
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
                when(state){
                    is GroupOverviewScreenState.LoadingScreenState -> {
                        LoadingIndicator()
                    }
                    is GroupOverviewScreenState.ErrorScreenState -> {

                    }
                    is GroupOverviewScreenState.ScreenStateWithData -> {
                        PaginatedLazyColum(
                            modifier = Modifier.fillMaxSize().padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                            items = state.groupsData,
                            loadNextPage = {
                                onEvent(GroupOverviewScreenEvents.LoadNextPage)
                            },
                            isLoading = false,
                            hasMore = state.pagination.hasNext,
                            advertisement = {
                                AdmobBanner()
                            }
                        ) { index, item ->
                            GroupListItem(
                                group = item,
                                onClick = {
                                    onEvent(GroupOverviewScreenEvents.OnGroupItemClicked(item.id))
                                }
                            )
                        }
                    }
                }
            }
        }
        else -> {}
    }

}

@Preview(device = Devices.PIXEL_XL)
@Composable
fun GroupOverviewScreenPreview() {
    TorqueLinkTheme {
        GroupOverviewScreen(
            windowSize = WindowSize.Small(0,0),
            state = GroupOverviewScreenState.ScreenStateWithData(
                pagination = Pagination(
                    1,
                    10,
                    true
                ),
                groupsData = listOf(
                    Groups.GroupDto(
                        id = 4356,
                        memberCount = 457,
                        eventCount = 325,
                        followerCount = 235,
                        groupName = "Test group",
                        description = "This is a test group",
                        logoUrl = null,
                        coverPhotoUrl = "https://autonxt.net/wp-content/uploads/2018/04/autocontentexp.com370z-drift_1200-f7d42d39ce1d4fb0551fc0a947576f88240181ad.jpg",
                        privateGroup = false,
                        joinRequestsEnabled = false,
                        memberListVisibility = MemberListVisibility.VISIBLE,
                        facebookUrl = null,
                        instagramUrl = null,
                        twitterUrl = null,
                        linkedInUrl = null,
                        websiteUrl = null
                    )
                )
            ),
            onEvent = {},
            snackBarHostState = remember { SnackbarHostState() }
        )
    }
}

@Preview(device = Devices.PIXEL_TABLET)
@Composable
fun TimeLineScreenTabletPreview() {
    TorqueLinkTheme {
        GroupOverviewScreen(
            windowSize = WindowSize.Medium(0,0),
            state = GroupOverviewScreenState.ScreenStateWithData(
                pagination = Pagination(
                    1,
                    10,
                    true
                ),
                groupsData = listOf(
                    Groups.GroupDto(
                        id = 4356,
                        memberCount = 154,
                        followerCount = 235,
                        eventCount = 325,
                        groupName = "Test group",
                        description = "This is a test group",
                        logoUrl = null,
                        coverPhotoUrl = null,
                        privateGroup = false,
                        joinRequestsEnabled = false,
                        memberListVisibility = MemberListVisibility.VISIBLE,
                        facebookUrl = null,
                        instagramUrl = null,
                        twitterUrl = null,
                        linkedInUrl = null,
                        websiteUrl = null
                    )
                )
            ),
            onEvent = {},
            snackBarHostState = remember { SnackbarHostState() }
        )
    }
}