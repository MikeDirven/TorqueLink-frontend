package nl.torquelink.presentation.screens.timeline

import nl.torquelink.shared.models.profile.UserProfiles

sealed interface TimeLineScreenState {
    val profile: UserProfiles.UserProfileWithSettingsDto?

    data class ScreenStateWithData(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null,
        val timeLineData: List<Any> = emptyList(),
    ) : TimeLineScreenState

    data class LoadingScreenState(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null
    ): TimeLineScreenState

    data class ErrorScreenState(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null,
    ): TimeLineScreenState
}