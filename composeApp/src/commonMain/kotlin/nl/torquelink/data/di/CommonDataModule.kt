package nl.torquelink.data.di

import nl.torquelink.data.network.di.CommonNetworkModule
import nl.torquelink.data.repositories.di.CommonRepositoriesModule
import org.koin.dsl.module

val CommonDataModule = module {
    includes(CommonNetworkModule)
    includes(CommonRepositoriesModule)
}