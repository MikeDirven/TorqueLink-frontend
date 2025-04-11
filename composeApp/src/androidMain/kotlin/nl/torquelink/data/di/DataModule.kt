package nl.torquelink.data.di

import nl.torquelink.data.datastore.di.PreferencesModule
import nl.torquelink.data.repositories.di.RepositoriesModule
import org.koin.dsl.module

val DataModule = module {
    includes(
        CommonDataModule,
        PreferencesModule,
        RepositoriesModule
    )
}