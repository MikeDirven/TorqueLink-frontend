package nl.torquelink.presentation.screens.timeline

import nl.torquelink.domain.enums.BaseScreenTabs

sealed interface TimeLineScreenEvents {
    data class OnTabSwitch(val tab: BaseScreenTabs) : TimeLineScreenEvents
}