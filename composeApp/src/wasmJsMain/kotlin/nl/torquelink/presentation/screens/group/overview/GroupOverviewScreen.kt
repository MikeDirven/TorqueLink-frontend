package nl.torquelink.presentation.screens.group.overview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import nl.torquelink.domain.enums.BaseScreenTabs
import nl.torquelink.domain.window.WindowSize
import nl.torquelink.domain.window.getCurrentWindowSize
import nl.torquelink.presentation.screens.generic.BaseCompactScreenLayout
import nl.torquelink.presentation.screens.generic.components.LoadingIndicator
import nl.torquelink.presentation.screens.generic.components.PaginatedLazyColum
import nl.torquelink.presentation.screens.group.components.GroupListItem

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
                    { AsyncImage(it, "") }
                }
            ) {
                when(state) {
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