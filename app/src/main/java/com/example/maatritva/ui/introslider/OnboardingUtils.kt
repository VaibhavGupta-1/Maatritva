package com.example.maatritva.ui.introslider

import android.content.Context
import androidx.core.content.edit

class OnboardingUtils(private val context: Context) {

    fun isOnboardingCompleted(): Boolean {
        return context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
            .getBoolean("completed", false)
    }

    fun setOnboardingCompleted() {
        context.getSharedPreferences("onboarding", Context.MODE_PRIVATE)
            .edit() {
                putBoolean("completed", true)
            }
    }

}