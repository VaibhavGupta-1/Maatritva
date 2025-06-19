package com.example.maatritva.ui.profile

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface userprofiledao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: userprofile)

    @Query("SELECT * FROM UserProfile LIMIT 1")
    suspend fun getProfile(): userprofile?
}
