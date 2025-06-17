package com.example.maatritva.ui.pregscreen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.*
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

    NavigationBar {
        items.forEach { (route, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = route) },
                selected = currentRoute == route,
                onClick = { navController.navigate(route) },
                label = { Text(route.replace("_", " ").capitalize()) }
            )
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Your Pregnancy Tracker 💖", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        DashboardCard("Due Date Calculator", "Check your expected delivery date", Icons.Default.DateRange) {
            navController.navigate("due_date")
        }
        DashboardCard("Daily Tips & Advice", "Health guidance for your week", Icons.Default.Lightbulb) {
            navController.navigate("tips")
        }
        DashboardCard("Symptoms Tracker", "Monitor symptoms & mood", Icons.Default.Healing) {
            navController.navigate("symptom_tracker")
        }
    }
}

@Composable
fun DashboardCard(title: String, subtitle: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(40.dp))
            Spacer(Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
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
    LazyColumn(Modifier.fillMaxSize().padding(16.dp)) {
        items(10) {
            Card(Modifier.padding(vertical = 8.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Week ${it + 1}", style = MaterialTheme.typography.titleMedium)
                    Text("Stay hydrated, rest well, and take prenatal vitamins.")
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
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Kick Counter", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Text("Kicks: $kicks", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            coroutineScope.launch {
                db.trackerDao().insertKick(KickEntry(count = 1))
                kicks++
            }
        }) {
            Text("Add Kick")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            coroutineScope.launch {
                db.trackerDao().clearKicks()
                kicks = 0
            }
        }) {
            Text("Reset")
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

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Weight Tracker", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Enter weight (kg)") }
        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            if (weight.isNotBlank()) {
                coroutineScope.launch {
                    db.trackerDao().insertWeight(WeightEntry(weight = weight))
                    weights = db.trackerDao().getAllWeights()
                    weight = ""
                }
            }
        }) {
            Text("Add Entry")
        }
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(weights.size) { index ->
                val entry = weights[index]
                Row(
                    Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Week ${index + 1}: ${entry.weight} kg")
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