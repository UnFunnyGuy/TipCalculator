package com.unfunny.tip.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.unfunny.tip.data.TipDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    fun providePreferenceDataStore(dataStore: DataStore<Preferences>): TipDatastore {
        return TipDatastore(dataStore)
    }

    private val Context.dataStore: DataStore<Preferences> by
        preferencesDataStore(name = "preferences")

    @Provides
    fun provideDataStore(@ApplicationContext app: Context): DataStore<Preferences> = app.dataStore
}
