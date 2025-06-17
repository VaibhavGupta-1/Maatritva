package com.example.maatritva.ui

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class MaastrichtApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}