package nl.torquelink.data.network.di

import nl.torquelink.data.network.TorqueLinkApi
import org.koin.dsl.module

val CommonNetworkModule = module {
    single { TorqueLinkApi }
}