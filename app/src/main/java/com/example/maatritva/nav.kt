package com.example.maatritva

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.maatritva.ui.Auth.AuthViewModel
import com.example.maatritva.ui.Auth.HomePage
import com.example.maatritva.ui.Auth.LoginPage
import com.example.maatritva.ui.Auth.SignupPage
import com.example.maatritva.ui.homescreen.HomeScr
import com.example.maatritva.ui.pregscreen.PregnancyTrackerApp

@SuppressLint("ViewModelConstructorInComposable")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            LoginPage(modifier,navController,authViewModel)
        }
        composable("signup"){
            SignupPage(modifier,navController,authViewModel)
        }
        composable("home1"){
            HomePage(modifier,navController,authViewModel)
        }
        composable("home screen"){
            PregnancyTrackerApp()
        }
        composable("home src"){
            HomeScr(
                navController = navController
            )
        }
    }
    )
}

