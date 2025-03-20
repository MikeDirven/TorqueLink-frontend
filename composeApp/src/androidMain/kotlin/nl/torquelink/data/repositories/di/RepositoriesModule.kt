package nl.torquelink.data.repositories.di

import nl.torquelink.domain.repositories.PreferencesRepository
import nl.torquelink.data.repositories.repositories.local.LocalPreferencesRepository
import nl.torquelink.data.repositories.repositories.remote.RemoteAuthenticationRepository
import nl.torquelink.data.repositories.repositories.remote.RemoteUsersRepository
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.domain.repositories.UsersRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val RepositoriesModule = module {
    singleOf(::LocalPreferencesRepository).bind<PreferencesRepository>()
    singleOf(::RemoteAuthenticationRepository).bind<AuthenticationRepository>()
    singleOf(::RemoteUsersRepository).bind<UsersRepository>()
}