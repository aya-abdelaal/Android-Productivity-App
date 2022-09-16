package com.ayaabdelaal.elevate.model.data

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Transformations.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException


private val MONTHLY_PREFERENCES_NAME = "monthly-DataStore"
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = MONTHLY_PREFERENCES_NAME
)

class MonthlyDataStore(private val context : Context) {


    companion object {
        private val THEME_KEY = stringPreferencesKey("monthly-theme")
        private val ABOUT_KEY = stringPreferencesKey("monthly-about")
    }


    suspend fun saveMonthly(theme : String, about:String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
            preferences[ABOUT_KEY] = about
        }
    }

    val getMonthlyTheme : Flow<String> =
         context.dataStore.data .catch { exception ->
             if (exception is IOException) {
                 Log.d("DataStore", exception.message.toString())
                 emit(emptyPreferences())
             } else {
                 throw exception
             }
         }
            .map { preferences ->
               preferences[THEME_KEY] ?: ""
            }


   val getMonthlyAbout : Flow<String> =
       context.dataStore.data .catch { exception ->
           if (exception is IOException) {
               Log.d("DataStore", exception.message.toString())
               emit(emptyPreferences())
           } else {
               throw exception
           }
       }
            .map { preferences ->
                preferences[ABOUT_KEY] ?: ""
            }

}