package nl.torquelink.presentation.group.overview

import nl.torquelink.domain.enums.BaseScreenTabs

sealed interface GroupOverviewScreenEvents {
    data class OnTabSwitch(val tab: BaseScreenTabs) : GroupOverviewScreenEvents
}