package com.example.maatritva

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.maatritva.ui.AppointmentCard
import com.example.maatritva.ui.AppointmentScreen
import com.example.maatritva.ui.Auth.AuthViewModel
import com.example.maatritva.ui.Auth.HomePage
import com.example.maatritva.ui.Auth.LoginPage
import com.example.maatritva.ui.Auth.SignupPage
import com.example.maatritva.ui.EmergencyContactRoute
import com.example.maatritva.ui.homescreen.AppHeader
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
import com.example.maatritva.ui.nutritions.MonthlyScreen
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen1
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen2
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen3
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen4
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen5
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen6
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen7
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen8
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen9
import com.example.maatritva.ui.nutritions.weekly.WeeklyScreen10
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd1
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd10
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd11
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd12
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd13
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd14
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd15
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd16
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd17
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd18
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd19
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd2
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd20
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd21
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd22
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd23
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd24
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd25
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd26
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd27
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd28
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd29
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd3
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd30
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd31
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd32
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd33
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd34
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd35
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd36
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd37
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd38
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd39
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd4
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd40
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd5
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd6
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd7
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd8
import com.example.maatritva.ui.nutritions.weekly.weekdetails.Weekd9
import com.example.maatritva.ui.pregscreen.DailyTipsScreen
import com.example.maatritva.ui.pregscreen.Header
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
        composable("Monthly") {
            MonthlyScreen(navController)
        }

        composable("Weekly Screen1()") { WeeklyScreen1(navController = navController) }
        composable("Weekly Screen2()") { WeeklyScreen2(navController = navController) }
        composable("Weekly Screen3()") { WeeklyScreen3(navController = navController) }
        composable("Weekly Screen4()") { WeeklyScreen4(navController = navController) }
        composable("Weekly Screen5()") { WeeklyScreen5(navController = navController) }
        composable("Weekly Screen6()") { WeeklyScreen6(navController = navController) }
        composable("Weekly Screen7()") { WeeklyScreen7(navController = navController) }
        composable("Weekly Screen8()") { WeeklyScreen8(navController = navController) }
        composable("Weekly Screen9()") { WeeklyScreen9(navController = navController) }
        composable("Weekly Screen10()") { WeeklyScreen10(navController = navController) }

        composable("weekd1") { Weekd1(navController) }
        composable("weekd2") { Weekd2(navController) }
        composable("weekd3") { Weekd3(navController) }
        composable("weekd4") { Weekd4(navController) }
        composable("weekd5") { Weekd5(navController) }
        composable("weekd6") { Weekd6(navController) }
        composable("weekd7") { Weekd7(navController) }
        composable("weekd8") { Weekd8(navController) }
        composable("weekd9") { Weekd9(navController) }
        composable("weekd10") { Weekd10(navController) }
        composable("weekd11") { Weekd11(navController) }
        composable("weekd12") { Weekd12(navController) }
        composable("weekd13") { Weekd13(navController) }
        composable("weekd14") { Weekd14(navController) }
        composable("weekd15") { Weekd15(navController) }
        composable("weekd16") { Weekd16(navController) }
        composable("weekd17") { Weekd17(navController) }
        composable("weekd18") { Weekd18(navController) }
        composable("weekd19") { Weekd19(navController) }
        composable("weekd20") { Weekd20(navController) }
        composable("weekd21") { Weekd21(navController) }
        composable("weekd22") { Weekd22(navController) }
        composable("weekd23") { Weekd23(navController) }
        composable("weekd24") { Weekd24(navController) }
        composable("weekd25") { Weekd25(navController) }
        composable("weekd26") { Weekd26(navController) }
        composable("weekd27") { Weekd27(navController) }
        composable("weekd28") { Weekd28(navController) }
        composable("weekd29") { Weekd29(navController) }
        composable("weekd30") { Weekd30(navController) }
        composable("weekd31") { Weekd31(navController) }
        composable("weekd32") { Weekd32(navController) }
        composable("weekd33") { Weekd33(navController) }
        composable("weekd34") { Weekd34(navController) }
        composable("weekd35") { Weekd35(navController) }
        composable("weekd36") { Weekd36(navController) }
        composable("weekd37") { Weekd37(navController) }
        composable("weekd38") { Weekd38(navController) }
        composable("weekd39") { Weekd39(navController) }
        composable("weekd40") { Weekd40(navController) }


        composable("Appointments") { Appointments() }

        composable("tips") { DailyTipsScreen() }


    }
    )
}

@Composable
fun Appointments() {
    Column {
        Header("Upcoming Appointments")

        Spacer(modifier = Modifier.height(20.dp))

        AppointmentCard(
            doctorName = "Dr. Alana Rueter",
            specialty = "Dentist Consultation",
            date = "Monday, 26 July",
            time = "9:00 - 10:00",
            isUpcoming = true,
            onReschedule = { /* Handle reschedule */ },
            onCancel = { /* Handle cancel */ },
            onCall = { /* Handle call */ },
            onMessage = { /* Handle message */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        AppointmentCard(
            doctorName = "Dr. Sarah Johnson",
            specialty = "Gynecologist Checkup",
            date = "Wednesday, 28 July",
            time = "2:00 - 3:00",
            isUpcoming = true,
            onReschedule = { /* Handle reschedule */ },
            onCancel = { /* Handle cancel */ },
            onCall = { /* Handle call */ },
            onMessage = { /* Handle message */ }
        )
    }
}






