package com.example.maatritva.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "profile_database"
    ).build()

    private val _profileState = MutableStateFlow<userprofile?>(null)
    val profileState: StateFlow<userprofile?> = _profileState.asStateFlow()

    fun saveProfile(name: String, dob: String, gender: String, imageUri: String?) {
        val profile = userprofile(name = name, dob = dob, gender = gender, imageUri = imageUri)
        viewModelScope.launch {
            db.userProfileDao().insertProfile(profile)
            _profileState.value = profile
        }
    }

    fun loadProfile() {
        viewModelScope.launch {
            val profile = db.userProfileDao().getProfile()
            _profileState.value = profile
        }
    }
}
