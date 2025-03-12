package nl.torquelink.presentation.screens.timeline

import nl.torquelink.shared.models.profile.UserProfiles

data class TimeLineScreenState(
    val timeLineData: List<Any> = emptyList(),
    val profile: UserProfiles.UserProfileWithSettingsDto? = null
)
