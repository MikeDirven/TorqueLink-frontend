package nl.torquelink.presentation.navigation.di

import nl.torquelink.presentation.navigation.navigator.DefaultNavigator
import nl.torquelink.presentation.navigation.navigator.Navigator
import org.koin.dsl.module

val CommonNavigationModule = module {
    single<Navigator> { DefaultNavigator }
}