package nl.torquelink.data.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import nl.torquelink.data.datastore.PreferencesDataSource
import nl.torquelink.data.datastore.dataStore


internal fun provideDatastore(context: Context): DataStore<Preferences> {
    return context.dataStore
}

internal fun providePreferencesDataSource(dataStore: DataStore<Preferences>) : PreferencesDataSource {
    return PreferencesDataSource(dataStore)
}