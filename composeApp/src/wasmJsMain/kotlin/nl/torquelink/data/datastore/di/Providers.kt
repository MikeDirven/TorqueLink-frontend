package nl.torquelink.data.datastore.di

import kotlinx.browser.window
import nl.torquelink.data.datastore.PreferencesDataSource
import org.w3c.dom.Storage


internal fun provideDatastore(): Storage {
    return window.localStorage
}

internal fun providePreferencesDataSource(dataStore: Storage) : PreferencesDataSource {
    return PreferencesDataSource(dataStore)
}