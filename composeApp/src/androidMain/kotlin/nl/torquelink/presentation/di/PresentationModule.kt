package nl.torquelink.presentation.di

import nl.torquelink.presentation.navigation.di.NavigationModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import nl.torquelink.presentation.screens.login.LoginScreenViewModel
import nl.torquelink.presentation.screens.register.RegisterScreenViewModel
import nl.torquelink.presentation.screens.reset.ResetPasswordScreenViewModel
import nl.torquelink.presentation.screens.timeline.TimeLineScreenViewModel
import nl.torquelink.presentation.snackbar.di.SnackBarModule

val PresentationModule = module {
    includes(NavigationModule)
    includes(SnackBarModule)

    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::RegisterScreenViewModel)
    viewModelOf(::ResetPasswordScreenViewModel)
    viewModelOf(::TimeLineScreenViewModel)
}