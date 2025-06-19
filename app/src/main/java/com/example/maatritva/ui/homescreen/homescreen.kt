package com.example.maatritva.ui.homescreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.filled.ChildFriendly
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.maatritva.ui.theme.Red40


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScr(navController: NavHostController) {

    var selectedBottomItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedBottomItem,
                navController = navController,
                onItemSelected = { selectedBottomItem = it }
            )
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .verticalScroll(scrollState)
                .padding(paddingValues)
//                .padding(horizontal = 16.dp)
        ) {

            AppHeader()
            Spacer(modifier = Modifier.height(18.dp))

            // Feature Cards Row
            LazyRow (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    ,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 10.dp)

            ) {
               item{ FeatureCard(
                   icon = Icons.Default.Analytics,
                   title = "Pregnancy\nTracker",
                   backgroundColor = Color(0xFFFFFFFF),
                   iconColor = Color(0xFF1976D2),
                   modifier = Modifier.weight(1f),
                   onClick = { navController.navigate("Pregnancy screen") }
               )
               }
                item{
                    FeatureCard(
                        icon = Icons.Default.LocationOn,
                        title = "Nearby\nHospitals",
                        backgroundColor = Color(0xFFFFFFFF),
                        iconColor = Color(0xFF1976D2),
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate("Hospital") }
                    )
                }
                item { FeatureCard(
                    icon = Icons.Default.CalendarToday,
                    title = "Appointments",
                    backgroundColor = Color(0xFFFFFFFF),
                    iconColor = Color(0xFF1976D2),
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("Appointment Screen") }
                )
                }
                item { FeatureCard(
                    icon = Icons.Default.Help,
                    title = "Queries",
                    backgroundColor = Color(0xFFFFFFFF),
                    iconColor = Color(0xFF1976D2),
                    modifier = Modifier.weight(1f),
                    onClick = { navController.navigate("Query Page") }
                )
                }
            }

            // Weekly Development Cards - Horizontally Scrollable
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(bottom = 24.dp).padding(horizontal = 10.dp)
            ) {

                items(getWeeklyDevelopmentData()) { weekData ->
                    WeeklyDevelopmentCard(
                        week = weekData.Month,
                        title = weekData.title,
                        description = weekData.description,
                        imageRes = weekData.imageRes,
                        onClick = {
                            navController.navigate(weekData.route)
                        }
                    )
                }
            }

            // Health Tips Section
            HealthTipsCard(
                onClick = { /* Navigate to Health Tips Detail */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Upcoming Appointments Section
            UpcomingAppointmentsCard(
                onClick = { /* Navigate to Appointments */ }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Emergency Contact Button
            Button(
                onClick = { navController.navigate("Emergency Screen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp).padding(horizontal = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD33560)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Emergency Contact",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            // Bottom spacing
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun FeatureCard(
    icon: ImageVector,
    title: String,
    backgroundColor: Color,
    iconColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.height(85.dp)
            .aspectRatio(1.5f)
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Red40,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
fun WeeklyDevelopmentCard(
    week: Int,
    title: String,
    description: String,
    imageRes: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(200.dp)
            .clickable { onClick() },
//            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image placeholder (replace with actual image)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        Color(0xFFD33560),
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Replace with actual image loading
                Icon(
                    imageVector = Icons.Filled.ChildFriendly,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun HealthTipsCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(

            ) }
            .padding(horizontal = 10.dp)
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Baby image placeholder
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        Color(0xFFD33560),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ChildFriendly,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Health Tips",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Daily tips for a healthy pregnancy",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun UpcomingAppointmentsCard(
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Baby image placeholder
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        Color(0xFFD33560),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ChildFriendly,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Upcoming Appointments",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Next: Dr. Smith on Oct 20",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    selectedItem: Int,
    navController: NavHostController,
    onItemSelected: (Int) -> Unit
    ) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Resources", Icons.Default.Book),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(90.dp)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 14.sp
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
                    when (item.title) {
                        "Home" -> navController.navigate("home src")
                        "Resources" -> navController.navigate("resources")
                        "Profile" -> navController.navigate("profile")
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFD33560),
                    selectedTextColor = Color(0xFFD33560),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color(0xFFE3F2FD)
                )
            )
        }
    }
}

// Data classes
data class WeeklyDevelopmentData(
    val Month: Int,
    val title: String,
    val description: String,
    val imageRes: Int,
    val route: String
)

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

// Sample data for weekly development - Extended list
fun getWeeklyDevelopmentData(): List<WeeklyDevelopmentData> {
    return listOf(
        WeeklyDevelopmentData(
            Month = 1,
            title = "Month 0 : Baby's Development",
            description = "Your baby is the size of a poppy seed",
            imageRes = 0,
            route = "week1"
        ),
        WeeklyDevelopmentData(
            Month = 2,
            title = "Month 1: Baby's Development",
            description = "Your baby is the size of a sesame seed",
            imageRes = 0,
            route = "week2"
        ),
        WeeklyDevelopmentData(
            Month = 3,
            title = "Month 2: Baby's Development",
            description = "Your baby is the size of a lentil",
            imageRes = 0,
            route = "week3"
        ),
        WeeklyDevelopmentData(
            Month = 4,
            title = "Month 3: Baby's Development",
            description = "Your baby is the size of a blueberry",
            imageRes = 0,
            route = "week4"
        ),
        WeeklyDevelopmentData(
            Month = 5,
            title = "Month 4: Baby's Development",
            description = "Your baby is the size of a kidney bean",
            imageRes = 0,
            route = "week5"
        ),
        WeeklyDevelopmentData(
            Month = 6,
            title = "Month 5: Baby's Development",
            description = "Your baby is the size of a grape",
            imageRes = 0,
            route = "week6"
        ),
        WeeklyDevelopmentData(
            Month = 7,
            title = "Month 6: Baby's Development",
            description = "Your baby is the size of a kumquat",
            imageRes = 0,
            route = "week7"
        ),
        WeeklyDevelopmentData(
            Month = 8,
            title = "Month 7: Baby's Development",
            description = "Your baby is the size of a fig",
            imageRes = 0,
            route = "week8"
        ),
        WeeklyDevelopmentData(
            Month = 9,
            title = "Month 8: Baby's Development",
            description = "Your baby is the size of a fig",
            imageRes = 0,
            route = "week9"
        ),
        WeeklyDevelopmentData(
            Month = 10,
            title = "Month 9: Baby's Development",
            description = "Your baby is the size of a fig",
            imageRes = 0,
            route = "week10"
        )

    )
}

@Composable
fun AppHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Red40)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Welcome, Mom",
            color = Color.White,
            fontSize = 22.sp
        )
    }
}



