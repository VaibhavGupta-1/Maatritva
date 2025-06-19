package com.example.maatritva.ui.pregscreen

import android.content.Context
import android.graphics.Paint
import android.graphics.Paint.Align
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.*
import com.example.maatritva.ui.theme.Red40
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "weight_entries")
data class WeightEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weight: String
)

@Entity(tableName = "kick_entries")
data class KickEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val count: Int
)

@Dao
interface TrackerDao {
    @Query("SELECT * FROM weight_entries")
    suspend fun getAllWeights(): List<WeightEntry>

    @Insert
    suspend fun insertWeight(entry: WeightEntry)

    @Delete
    suspend fun deleteWeight(entry: WeightEntry)

    @Query("SELECT * FROM kick_entries")
    suspend fun getAllKicks(): List<KickEntry>

    @Insert
    suspend fun insertKick(entry: KickEntry)

    @Query("DELETE FROM kick_entries")
    suspend fun clearKicks()
}

@Database(entities = [WeightEntry::class, KickEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackerDao(): TrackerDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pregnancy_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PregnancyTrackerApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") { HomeScreen(navController) }
            composable("due_date") { DueDateCalculatorScreen() }
            composable("tips") { DailyTipsScreen() }
            composable("kick_counter") { KickCounterScreen() }
            composable("weight_tracker") { WeightTrackerScreen() }
            composable("symptom_tracker") { SymptomTrackerScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        "home" to Icons.Default.Home,
        "kick_counter" to Icons.Default.TouchApp,
        "weight_tracker" to Icons.Default.MonitorWeight,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = Color.White,
        modifier = Modifier.height(90.dp)) {
        items.forEach { (route, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = route) },
                selected = currentRoute == route,
                onClick = { navController.navigate(route) },
                label = { Text(route.replace("_", " ").capitalize()) },
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

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFFF5F5F5))
        )
    {

        AppHeader("Your Pregnancy Tracker",)
        Spacer(Modifier.height(16.dp))

        DashboardCard("Due Date Calculator", "Check your expected delivery date", Icons.Default.DateRange, onClick =  {
            navController.navigate("due_date")
        },
            backgroundColor = Color(0xFFFFFFFF))
        DashboardCard("Daily Tips & Advice", "Health guidance for your week", Icons.Default.Lightbulb, onClick =  {
            navController.navigate("tips")
        },
            backgroundColor = Color(0xFFFFFFFF))
        DashboardCard("Symptoms Tracker", "Monitor symptoms & mood", Icons.Default.Healing, onClick =  {
            navController.navigate("symptom_tracker")
        },
            backgroundColor = Color(0xFFFFFFFF))
    }
}

@Composable
fun DashboardCard(title: String, subtitle: String, icon: ImageVector, onClick: () -> Unit,
                  backgroundColor: Color) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
            .padding(horizontal = 10.dp)
        ,
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(40.dp), tint = Red40)
            Spacer(Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Black)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DueDateCalculatorScreen() {
    var lmpDate by remember { mutableStateOf("") }
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dueDate = remember(lmpDate) {
        try {
            val lmp = LocalDate.parse(lmpDate, formatter)
            lmp.plusDays(280).toString()
        } catch (e: Exception) {
            "Invalid date"
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Enter Last Menstrual Period (yyyy-MM-dd)", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = lmpDate,
            onValueChange = { lmpDate = it },
            label = { Text("LMP Date") },
            placeholder = { Text("yyyy-MM-dd") }
        )
        Spacer(Modifier.height(16.dp))
        Text("Estimated Due Date: $dueDate", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DailyTipsScreen() {
    val tipsList = listOf(
        "Stay hydrated—aim for at least 8–10 glasses of water daily.",
        "Eat small, frequent meals to help reduce morning sickness.",
        "Get plenty of rest – your body is doing a lot of work!",
        "Take your prenatal vitamins daily as prescribed.",
        "Do light exercises or walking to improve circulation.",
        "Avoid alcohol, tobacco, caffeine, and undercooked foods.",
        "Sleep on your left side and use pillows for comfort.",
        "Maintain regular doctor appointments and checkups.",
        "Talk openly about how you’re feeling emotionally.",
        "Keep a pregnancy journal to track milestones and thoughts.",
        "Attend all scans: NT (12 weeks), Anomaly (20 weeks), and Growth (3rd trimester).",
        "Pack your hospital bag by week 34 and discuss your birth plan.",
        "Eat a balanced diet rich in iron, calcium, and protein.",

    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(tipsList.size) { index ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Advice #${index + 1}", style = MaterialTheme.typography.titleMedium)
                    Text(tipsList[index])
                }
            }
        }
    }
}


@Composable
fun KickCounterScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    var kicks by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        val allKicks = db.trackerDao().getAllKicks()
        kicks = allKicks.sumOf { it.count }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))
    ) {
        AppHeader("Kick Counter")
        Spacer(Modifier.height(226.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Kicks: $kicks", style = MaterialTheme.typography.titleLarge,fontSize = 40.sp)

            Spacer(Modifier.height(16.dp))

            val addInteraction = remember { MutableInteractionSource() }
            val resetInteraction = remember { MutableInteractionSource() }
            val isAddPressed by addInteraction.collectIsPressedAsState()
            val isResetPressed by resetInteraction.collectIsPressedAsState()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Add Kick Button
                Button(
                    onClick = {
                        coroutineScope.launch {
                            db.trackerDao().insertKick(KickEntry(count = 1))
                            kicks++
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isAddPressed) Color.Green else Red40,
                        contentColor = Color.White
                    ),
                    interactionSource = addInteraction
                ) {
                    Text("Add Kick")
                }

                // Reset Button
                Button(
                    onClick = {
                        coroutineScope.launch {
                            db.trackerDao().clearKicks()
                            kicks = 0
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isResetPressed) Color.Red else Red40,
                        contentColor = Color.White
                    ),
                    interactionSource = resetInteraction
                ) {
                    Text("Reset")
                }
            }



        }

    }
}

@Composable
fun WeightTrackerScreen() {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    var weight by remember { mutableStateOf("") }
    var weights by remember { mutableStateOf<List<WeightEntry>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        weights = db.trackerDao().getAllWeights()
    }

    Column(Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {
        AppHeader("Weight Tracker")
        Spacer(Modifier.height(16.dp))
        Row(  modifier = Modifier
            .fillMaxWidth()

            ,
            verticalAlignment = Alignment.CenterVertically,
            ) { OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Enter weight (kg)") }, modifier = Modifier.padding(10.dp)
        )
            Button(onClick = {
                if (weight.isNotBlank()) {
                    coroutineScope.launch {
                        db.trackerDao().insertWeight(WeightEntry(weight = weight))
                        weights = db.trackerDao().getAllWeights()
                        weight = ""
                    }
                }
            }
//                , modifier = Modifier.padding(top)
            ) {
                Text("Entry")
            }
        }

        Spacer(Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(weights.size) { index ->
                val entry = weights[index]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .background(
                            color = Color(0xFFE3F2FD),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Week ${index + 1}: ${entry.weight} kg", style = MaterialTheme.typography.titleMedium)
                        IconButton(onClick = {
                            coroutineScope.launch {
                                db.trackerDao().deleteWeight(entry)
                                weights = db.trackerDao().getAllWeights()
                            }
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun SymptomTrackerScreen() {
    val symptoms = listOf("Nausea", "Fatigue", "Headache", "Back pain", "Cravings")
    val selected = remember { mutableStateMapOf<String, Boolean>() }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Symptom Tracker", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        symptoms.forEach { symptom ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selected[symptom] == true,
                    onCheckedChange = { selected[symptom] = it }
                )
                Text(symptom)
            }
        }
    }
}

@Composable
fun AppHeader(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Red40,
    textColor: Color = Color.White
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(top = 5.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = text,
            color = textColor,
            fontSize = 22.sp
        )
    }
}