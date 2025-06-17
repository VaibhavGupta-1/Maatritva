package com.example.maatritva

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier

import com.example.maatritva.ui.homescreen.HomeScreen
import com.example.maatritva.ui.pregscreen.PregnancyTrackerApp

import com.example.maatritva.ui.Auth.AuthViewModel
import com.example.maatritva.ui.introslider.OnboardingScreen
import com.example.maatritva.ui.introslider.OnboardingUtils

import com.example.maatritva.ui.theme.MaatritvaTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val onboardingUtils by lazy { OnboardingUtils(this) }

        val authViewModel : AuthViewModel by viewModels()
        setContent {
            MaatritvaTheme {

                Surface(color = MaterialTheme.colorScheme.background){
                    if (onboardingUtils.isOnboardingCompleted()) {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyAppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        authViewModel = authViewModel
                    ) }} else {
                            ShowOnboardingScreen()
                        }
                }
                }
                }
            }

        @Composable
        fun ShowOnboardingScreen() {
            val scope = rememberCoroutineScope()
            OnboardingScreen {
//                onboardingUtils.setOnboardingCompleted()
                scope.launch {
                    val authViewModel : AuthViewModel by viewModels()

                    setContent {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            MyAppNavigation(
                                modifier = Modifier.padding(innerPadding),
                                authViewModel = authViewModel
                            )
                        }
                    }
                }
            }
        }
        }



