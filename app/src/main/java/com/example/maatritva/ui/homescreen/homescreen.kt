package com.example.maatritva.ui.homescreen

import androidx.compose.material.icons.filled.ChildFriendly
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var selectedBottomItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedBottomItem,
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
                .padding(horizontal = 16.dp)
        ) {
            // Status Bar Space
            Spacer(modifier = Modifier.height(24.dp))

            // Welcome Header
            Text(
                text = "Welcome, Mom!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Feature Cards Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FeatureCard(
                    icon = Icons.Default.Analytics,
                    title = "Pregnancy\nTracker",
                    backgroundColor = Color(0xFFE3F2FD),
                    iconColor = Color(0xFF1976D2),
                    modifier = Modifier.weight(1f),
                    onClick = { /* Navigate to Pregnancy Tracker */ }
                )
                Spacer(modifier = Modifier.width(8.dp))
                FeatureCard(
                    icon = Icons.Default.Favorite,
                    title = "Health\nTips",
                    backgroundColor = Color(0xFFF3E5F5),
                    iconColor = Color(0xFF9C27B0),
                    modifier = Modifier.weight(1f),
                    onClick = { /* Navigate to Health Tips */ }
                )
                Spacer(modifier = Modifier.width(8.dp))
                FeatureCard(
                    icon = Icons.Default.CalendarToday,
                    title = "Appointments",
                    backgroundColor = Color(0xFFE3F2FD),
                    iconColor = Color(0xFF1976D2),
                    modifier = Modifier.weight(1f),
                    onClick = { /* Navigate to Appointments */ }
                )
                Spacer(modifier = Modifier.width(8.dp))
                FeatureCard(
                    icon = Icons.Default.Help,
                    title = "Queries",
                    backgroundColor = Color(0xFFE3F2FD),
                    iconColor = Color(0xFF1976D2),
                    modifier = Modifier.weight(1f),
                    onClick = { /* Navigate to Queries */ }
                )
            }

            // Weekly Development Cards - Horizontally Scrollable
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(bottom = 24.dp)
            ) {
                items(getWeeklyDevelopmentData()) { weekData ->
                    WeeklyDevelopmentCard(
                        week = weekData.week,
                        title = weekData.title,
                        description = weekData.description,
                        imageRes = weekData.imageRes,
                        onClick = {
                            /* Navigate to week detail screen */
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
                onClick = { /* Handle emergency contact */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
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
        modifier = modifier
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(32.dp)
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
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                        Color(0xFFB440C7),
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
            .clickable { onClick() },
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
                        Color(0xFFBD94E0),
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
            .clickable { onClick() },
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
                        Color(0xFFBD94E0),
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
    onItemSelected: (Int) -> Unit
) {
    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Resources", Icons.Default.Book),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(80.dp)
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
                        fontSize = 12.sp
                    )
                },
                selected = selectedItem == index,
                onClick = { onItemSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF2196F3),
                    selectedTextColor = Color(0xFF2196F3),
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
    val week: Int,
    val title: String,
    val description: String,
    val imageRes: Int
)

data class BottomNavItem(
    val title: String,
    val icon: ImageVector
)

// Sample data for weekly development - Extended list
fun getWeeklyDevelopmentData(): List<WeeklyDevelopmentData> {
    return listOf(
        WeeklyDevelopmentData(
            week = 4,
            title = "Week 4: Baby's Development",
            description = "Your baby is the size of a poppy seed",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 5,
            title = "Week 5: Baby's Development",
            description = "Your baby is the size of a sesame seed",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 6,
            title = "Week 6: Baby's Development",
            description = "Your baby is the size of a lentil",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 7,
            title = "Week 7: Baby's Development",
            description = "Your baby is the size of a blueberry",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 8,
            title = "Week 8: Baby's Development",
            description = "Your baby is the size of a kidney bean",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 9,
            title = "Week 9: Baby's Development",
            description = "Your baby is the size of a grape",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 10,
            title = "Week 10: Baby's Development",
            description = "Your baby is the size of a kumquat",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 11,
            title = "Week 11: Baby's Development",
            description = "Your baby is the size of a fig",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 12,
            title = "Week 12: Baby's Development",
            description = "Your baby is the size of a lime",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 13,
            title = "Week 13: Baby's Development",
            description = "Your baby is the size of a peach",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 14,
            title = "Week 14: Baby's Development",
            description = "Your baby is the size of a lemon",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 15,
            title = "Week 15: Baby's Development",
            description = "Your baby is the size of an apple",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 16,
            title = "Week 16: Baby's Development",
            description = "Your baby is the size of an avocado",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 17,
            title = "Week 17: Baby's Development",
            description = "Your baby is the size of a turnip",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 18,
            title = "Week 18: Baby's Development",
            description = "Your baby is the size of a bell pepper",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 19,
            title = "Week 19: Baby's Development",
            description = "Your baby is the size of a tomato",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 20,
            title = "Week 20: Baby's Development",
            description = "Your baby is the size of a banana",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 21,
            title = "Week 21: Baby's Development",
            description = "Your baby is the size of a carrot",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 22,
            title = "Week 22: Baby's Development",
            description = "Your baby is the size of a papaya",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 23,
            title = "Week 23: Baby's Development",
            description = "Your baby is the size of a large mango",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 24,
            title = "Week 24: Baby's Development",
            description = "Your baby is the size of an ear of corn",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 25,
            title = "Week 25: Baby's Development",
            description = "Your baby is the size of a rutabaga",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 26,
            title = "Week 26: Baby's Development",
            description = "Your baby is the size of a scallion",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 27,
            title = "Week 27: Baby's Development",
            description = "Your baby is the size of a cauliflower",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 28,
            title = "Week 28: Baby's Development",
            description = "Your baby is the size of an eggplant",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 29,
            title = "Week 29: Baby's Development",
            description = "Your baby is the size of a butternut squash",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 30,
            title = "Week 30: Baby's Development",
            description = "Your baby is the size of a cabbage",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 31,
            title = "Week 31: Baby's Development",
            description = "Your baby is the size of a coconut",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 32,
            title = "Week 32: Baby's Development",
            description = "Your baby is the size of a jicama",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 33,
            title = "Week 33: Baby's Development",
            description = "Your baby is the size of a pineapple",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 34,
            title = "Week 34: Baby's Development",
            description = "Your baby is the size of a cantaloupe",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 35,
            title = "Week 35: Baby's Development",
            description = "Your baby is the size of a honeydew melon",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 36,
            title = "Week 36: Baby's Development",
            description = "Your baby is the size of a romaine lettuce",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 37,
            title = "Week 37: Baby's Development",
            description = "Your baby is the size of a swiss chard",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 38,
            title = "Week 38: Baby's Development",
            description = "Your baby is the size of a winter melon",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 39,
            title = "Week 39: Baby's Development",
            description = "Your baby is the size of a pumpkin",
            imageRes = 0
        ),
        WeeklyDevelopmentData(
            week = 40,
            title = "Week 40: Baby's Development",
            description = "Your baby is the size of a watermelon",
            imageRes = 0
        )
    )
}



