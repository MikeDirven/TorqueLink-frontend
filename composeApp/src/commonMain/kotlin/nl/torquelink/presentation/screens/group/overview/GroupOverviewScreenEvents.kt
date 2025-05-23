package nl.torquelink.presentation.screens.group.overview

import nl.torquelink.domain.enums.BaseScreenTabs

sealed interface GroupOverviewScreenEvents {
    data class OnTabSwitch(val tab: BaseScreenTabs) : GroupOverviewScreenEvents
    data class OnGroupItemClicked(val groupId: Long) : GroupOverviewScreenEvents
    data object LoadNextPage : GroupOverviewScreenEvents
}