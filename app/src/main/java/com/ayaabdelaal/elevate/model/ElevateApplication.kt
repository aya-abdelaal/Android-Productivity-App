package com.ayaabdelaal.elevate.model

import android.app.Application
import com.ayaabdelaal.elevate.model.data.ElevateDatabase

class ElevateApplication : Application() {
    val database: ElevateDatabase by lazy { ElevateDatabase.getDatabase(this) }
}