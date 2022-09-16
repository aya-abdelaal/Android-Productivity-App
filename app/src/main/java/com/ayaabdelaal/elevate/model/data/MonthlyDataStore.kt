package com.ayaabdelaal.elevate.model.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Transformations.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val MONTHLY_PREFERENCES_NAME = "monthly_DataStore"


class MonthlyDataStore(private val context : Context) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
        name = MONTHLY_PREFERENCES_NAME
    )

    companion object {
        private val THEME_KEY = stringPreferencesKey("monthly_theme")
        private val ABOUT_KEY = stringPreferencesKey("monthly_about")
    }


    suspend fun saveMonthly(theme : String, about:String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
            preferences[ABOUT_KEY] = about
        }
    }

    fun getMonthlyTheme() =
         context.dataStore.data
            .map { preferences ->
                preferences[THEME_KEY] ?: ""
            }


   fun getMonthlyAbout() =
       context.dataStore.data
            .map { preferences ->
                preferences[ABOUT_KEY] ?: ""
            }

}