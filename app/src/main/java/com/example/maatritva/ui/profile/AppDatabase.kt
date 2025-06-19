package com.example.maatritva.ui.profile

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [userprofile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): userprofiledao
}
