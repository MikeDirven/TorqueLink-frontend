package nl.torquelink.data.repositories.di

import nl.torquelink.data.repositories.local.LocalPreferencesRepository
import nl.torquelink.domain.repositories.PreferencesRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val RepositoriesModule = module {
    singleOf(::LocalPreferencesRepository).bind<PreferencesRepository>()
}