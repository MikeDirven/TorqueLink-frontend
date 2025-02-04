package nl.torquelink.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val PREFERENCES_DATA_STORE = "preferences_datastore"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_DATA_STORE
)