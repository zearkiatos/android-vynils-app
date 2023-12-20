package com.androidvynils.app

import android.app.Application
import com.androidvynils.app.database.VinylRoomDatabase

class VinylsApplication: Application() {
    val database by lazy { VinylRoomDatabase.getDatabase(this) }
}