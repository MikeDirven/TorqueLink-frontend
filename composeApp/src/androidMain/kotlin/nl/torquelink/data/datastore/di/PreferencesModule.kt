package nl.torquelink.data.datastore.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import nl.torquelink.data.datastore.PreferencesDataSource

val PreferencesModule = module {
    single { provideDatastore(androidContext()) }
    singleOf(::PreferencesDataSource)
}