package nl.torquelink.presentation.di

import nl.torquelink.presentation.navigation.di.CommonNavigationModule
import nl.torquelink.presentation.screens.group.information.GroupInformationScreenViewModel
import nl.torquelink.presentation.screens.group.overview.GroupOverviewScreenViewModel
import nl.torquelink.presentation.screens.login.LoginScreenViewModel
import nl.torquelink.presentation.screens.profile.create.ProfileCreateScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import nl.torquelink.presentation.screens.register.RegisterScreenViewModel
import nl.torquelink.presentation.screens.reset.ResetPasswordScreenViewModel
import nl.torquelink.presentation.screens.timeline.TimeLineScreenViewModel
import nl.torquelink.presentation.snackbar.di.CommonSnackBarModule

actual val PresentationModule = module {
    includes(CommonNavigationModule)
    includes(CommonSnackBarModule)

    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::RegisterScreenViewModel)
    viewModelOf(::ResetPasswordScreenViewModel)
    viewModelOf(::ProfileCreateScreenViewModel)

    viewModelOf(::TimeLineScreenViewModel)

    // Groups
    viewModelOf(::GroupOverviewScreenViewModel)
    viewModelOf(::GroupInformationScreenViewModel)
}