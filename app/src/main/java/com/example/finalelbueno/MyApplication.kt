package com.example.finalelbueno

import android.app.Application
import com.example.finalelbueno.database.DatabaseManager

open class MyApplication: Application() {
    override fun onCreate() {
        DatabaseManager.instance.initializeDb(applicationContext)
        super.onCreate()
    }
}