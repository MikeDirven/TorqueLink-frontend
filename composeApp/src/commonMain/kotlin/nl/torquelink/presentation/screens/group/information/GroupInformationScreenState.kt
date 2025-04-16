package nl.torquelink.presentation.screens.group.information

import nl.torquelink.shared.models.group.Groups
import nl.torquelink.shared.models.profile.UserProfiles

sealed interface GroupInformationScreenState {
    val profile: UserProfiles.UserProfileWithSettingsDto?

    data class ScreenStateWithData(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null,
        val groupData: Groups.GroupWithDetailsDto? = null
    ) : GroupInformationScreenState

    data class LoadingScreenState(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null
    ): GroupInformationScreenState

    data class ErrorScreenState(
        override val profile: UserProfiles.UserProfileWithSettingsDto? = null,
    ): GroupInformationScreenState
}
