package com.example.maatritva

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class MaatritvaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
} 