package com.ayaabdelaal.elevate.model

import android.app.Application
import com.ayaabdelaal.elevate.model.data.ItemDatabase

class ElevateApplication : Application() {
    val database: ItemDatabase by lazy { ItemDatabase.getDatabase(this) }
}