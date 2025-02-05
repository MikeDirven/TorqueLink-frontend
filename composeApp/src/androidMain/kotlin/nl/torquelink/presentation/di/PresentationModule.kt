package nl.torquelink.presentation.di

import nl.torquelink.presentation.navigation.di.NavigationModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import nl.torquelink.presentation.screens.login.LoginScreenViewModel
import nl.torquelink.presentation.screens.register.RegisterScreenViewModel

val PresentationModule = module {
    includes(NavigationModule)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::RegisterScreenViewModel)
}