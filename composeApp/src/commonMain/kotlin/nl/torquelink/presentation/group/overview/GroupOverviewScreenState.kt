package nl.torquelink.presentation.group.overview

import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.models.profile.UserProfiles

data class GroupOverviewScreenState(
    val groupsData: List<Groups.GroupDto> = emptyList(),
    val profile: UserProfiles.UserProfileWithSettingsDto? = null
)
