package com.ayaabdelaal.elevate.model

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.ayaabdelaal.elevate.model.data.ElevateDatabase
import com.ayaabdelaal.elevate.model.data.MonthlyDataStore
import java.util.prefs.Preferences

class ElevateApplication : Application() {
    val database: ElevateDatabase by lazy { ElevateDatabase.getDatabase(this) }

}