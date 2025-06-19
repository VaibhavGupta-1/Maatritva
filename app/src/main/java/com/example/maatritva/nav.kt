package com.example.maatritva

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.maatritva.ui.AppointmentScreen
import com.example.maatritva.ui.Auth.AuthViewModel
import com.example.maatritva.ui.Auth.HomePage
import com.example.maatritva.ui.Auth.LoginPage
import com.example.maatritva.ui.Auth.SignupPage
import com.example.maatritva.ui.EmergencyContactRoute
import com.example.maatritva.ui.EmergencyContactScreenContent
import com.example.maatritva.ui.homescreen.HomeScr
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week1Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week2Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week3Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week4Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week5Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week6Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week7Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week8Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week9Screen
import com.example.maatritva.ui.homescreen.monthlydevelopmentscreens.Week10Screen
import com.example.maatritva.ui.map.Hospital

import com.example.maatritva.ui.pregscreen.PregnancyTrackerApp
import com.example.maatritva.ui.profile.ProfileScreen
import com.example.maatritva.ui.profile.ProfileViewModel
import com.example.maatritva.ui.queries.ChatBot
import com.example.maatritva.ui.queries.ChatViewModel

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

        composable("home src"){
            HomeScr(
                navController = navController
            )
        }
        composable("Pregnancy screen"){
            PregnancyTrackerApp()
        }
        composable("Appointment Screen"){
            AppointmentScreen(
                navController = navController
            )
        }
        composable("Query Page") {
            val chatViewModel: ChatViewModel = viewModel()
            ChatBot(
                modifier = modifier,
                viewModel = chatViewModel
            )
        }
        composable("week1") { Week1Screen() }
        composable("week2") { Week2Screen() }
        composable("week3") { Week3Screen() }
        composable("week4") { Week4Screen() }
        composable("week5") { Week5Screen() }
        composable("week6") { Week6Screen() }
        composable("week7") { Week7Screen() }
        composable("week8") { Week8Screen() }
        composable("week9") { Week9Screen() }
        composable("week10") { Week10Screen() }

        composable("profile") {
            val context = LocalContext.current
            val viewModel: ProfileViewModel = viewModel(
                factory = ViewModelProvider.AndroidViewModelFactory.getInstance(context.applicationContext as Application)
            )
            ProfileScreen(viewModel = viewModel,navController)
        }
        composable("Emergency Screen") {EmergencyContactRoute()
        }
        composable ("Hospital"){ Hospital() }


    }
    )
}





