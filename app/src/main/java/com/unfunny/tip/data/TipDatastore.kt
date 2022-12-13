package com.unfunny.tip.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TipDatastore @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        val STORED_PERCENTAGE = doublePreferencesKey("stored_percentage")
    }

    val tipPercentage: Flow<Double?> =
        dataStore.data.map { preferences -> preferences[STORED_PERCENTAGE] }

    suspend fun savePercentage(percentage: Double) {
        dataStore.edit { preferences -> preferences[STORED_PERCENTAGE] = percentage }
    }
}
