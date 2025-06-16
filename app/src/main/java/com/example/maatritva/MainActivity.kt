package com.example.maatritva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.maatritva.ui.homescreen.HomeScreen
import com.example.maatritva.ui.pregscreen.PregnancyTrackerApp
import com.example.maatritva.ui.theme.MaatritvaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaatritvaTheme {
                HomeScreen()
//                PregnancyTrackerApp()
                }
            }
        }
    }


