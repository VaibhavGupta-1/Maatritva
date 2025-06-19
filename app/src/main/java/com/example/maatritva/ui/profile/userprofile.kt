package com.example.maatritva.ui.profile

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class userprofile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dob: String,
    val gender: String
)
