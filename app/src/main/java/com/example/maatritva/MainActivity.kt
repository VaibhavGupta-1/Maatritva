package com.example.maatritva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.maatritva.ui.theme.MaatritvaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaatritvaTheme {

                Home()
                }
            }
        }
    }

@Composable
fun Home(modifier: Modifier = Modifier) {
    print("Hello")

}
