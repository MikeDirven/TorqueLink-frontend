package nl.torquelink.data.repositories.di

import nl.torquelink.data.repositories.remote.RemoteAuthenticationRepository
import nl.torquelink.data.repositories.remote.RemoteGroupsRepository
import nl.torquelink.data.repositories.remote.RemoteUsersRepository
import nl.torquelink.domain.repositories.AuthenticationRepository
import nl.torquelink.domain.repositories.GroupsRepository
import nl.torquelink.domain.repositories.UsersRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val CommonRepositoriesModule = module {
    singleOf(::RemoteAuthenticationRepository).bind<AuthenticationRepository>()
    singleOf(::RemoteUsersRepository).bind<UsersRepository>()
    singleOf(::RemoteGroupsRepository).bind<GroupsRepository>()
}