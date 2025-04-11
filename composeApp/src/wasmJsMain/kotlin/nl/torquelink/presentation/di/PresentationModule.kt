package nl.torquelink.presentation.di

import nl.torquelink.presentation.group.overview.GroupOverviewScreenViewModel
import nl.torquelink.presentation.navigation.di.CommonNavigationModule
import nl.torquelink.presentation.screens.login.LoginScreenViewModel
import nl.torquelink.presentation.screens.profile.create.ProfileCreateScreenViewModel
import nl.torquelink.presentation.screens.register.RegisterScreenViewModel
import nl.torquelink.presentation.screens.reset.ResetPasswordScreenViewModel
import nl.torquelink.presentation.screens.timeline.TimeLineScreenViewModel
import nl.torquelink.presentation.snackbar.di.CommonSnackBarModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val PresentationModule: Module = module {
    includes(CommonNavigationModule)
    includes(CommonSnackBarModule)

    singleOf(::LoginScreenViewModel)
    singleOf(::RegisterScreenViewModel)
    singleOf(::ResetPasswordScreenViewModel)
    singleOf(::ProfileCreateScreenViewModel)
    singleOf(::TimeLineScreenViewModel)
    singleOf(::GroupOverviewScreenViewModel)
}