package nl.torquelink.presentation.screens.group.information

import nl.torquelink.domain.enums.BaseScreenTabs

sealed interface GroupInformationScreenEvents {
    data class OnTabSwitch(val tab: BaseScreenTabs) : GroupInformationScreenEvents
    data class GetGroupDetails(val groupId: Long) : GroupInformationScreenEvents
}