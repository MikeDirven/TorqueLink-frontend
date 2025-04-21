package nl.torquelink.presentation.screens.group.overview

import nl.torquelink.domain.Pagination
import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.models.profile.UserProfiles

sealed interface GroupOverviewScreenState {
    val profile: UserProfiles.UserProfileWithSettingsDto?

    data class ScreenStateWithData(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null,
        val pagination: Pagination,
        val groupsData: List<Groups.GroupDto> = emptyList(),
    ) : GroupOverviewScreenState

    data class LoadingScreenState(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null
    ): GroupOverviewScreenState

    data class ErrorScreenState(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null,
    ): GroupOverviewScreenState
}
