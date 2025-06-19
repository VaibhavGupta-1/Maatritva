package com.example.maatritva.ui

import android.app.Application
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Message
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.*
import com.example.maatritva.R
import com.example.maatritva.ui.theme.DarkPink
import com.example.maatritva.ui.theme.LightBlue
import com.example.maatritva.ui.theme.MediumPink
import com.example.maatritva.ui.theme.White
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


@Entity(tableName = "doctors")
data class Doctor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val specialty: String,
    val phoneNumber: String
)

@Dao
interface DoctorDao {
    @Insert
    suspend fun insert(doctor: Doctor)

    @Update
    suspend fun update(doctor: Doctor)

    @Delete
    suspend fun delete(doctor: Doctor)

    @Query("SELECT * FROM doctors ORDER BY name ASC")
    fun getAllDoctors(): kotlinx.coroutines.flow.Flow<List<Doctor>>
}

@Database(entities = [Doctor::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun doctorDao(): DoctorDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class DoctorRepository(private val doctorDao: DoctorDao) {
    val allDoctors: kotlinx.coroutines.flow.Flow<List<Doctor>> = doctorDao.getAllDoctors()

    suspend fun insert(doctor: Doctor) {
        doctorDao.insert(doctor)
    }

    suspend fun update(doctor: Doctor) {
        doctorDao.update(doctor)
    }

    suspend fun delete(doctor: Doctor) {
        doctorDao.delete(doctor)
    }
}

class DoctorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DoctorRepository
    private val _doctors = MutableStateFlow<List<Doctor>>(emptyList())
    val doctors: StateFlow<List<Doctor>> = _doctors.asStateFlow()

    init {
        val doctorDao = AppDatabase.getDatabase(application).doctorDao()
        repository = DoctorRepository(doctorDao)
        // Collect Flow and update StateFlow
        viewModelScope.launch {
            repository.allDoctors.collect { list ->
                _doctors.value = list
            }
        }
    }

    fun insert(doctor: Doctor) = viewModelScope.launch {
        repository.insert(doctor)
    }

    fun update(doctor: Doctor) = viewModelScope.launch {
        repository.update(doctor)
    }

    fun delete(doctor: Doctor) = viewModelScope.launch {
        repository.delete(doctor)
    }
}

@Composable
fun EmergencyContactRoute(doctorViewModel: DoctorViewModel = viewModel()) {
    val doctors by doctorViewModel.doctors.collectAsState()
    EmergencyContactScreenContent(
        doctors = doctors,
        onAddDoctor = { name, specialty, phone ->
            if (name.isNotBlank() && specialty.isNotBlank() && phone.isNotBlank()) {
                doctorViewModel.insert(Doctor(name = name, specialty = specialty, phoneNumber = phone))
            }
        },
        onCallAction = { /* TODO: Implement actual call action via ViewModel or Intent */ },
        onMessageAction = { /* TODO: Implement actual message action via ViewModel or Intent */ }
    )
}

@Composable
fun EmergencyContactScreenContent(
    doctors: List<Doctor>,
    onAddDoctor: (String, String, String) -> Unit,
    onCallAction: () -> Unit,
    onMessageAction: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var specialty by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp)
    ) {
        Text(
            text = "Emergency Contacts",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(containerColor = LightBlue.copy(alpha = 0.15f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Add Doctor Contact",
                    style = MaterialTheme.typography.titleMedium,
                    color = DarkPink,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 20.sp
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = specialty,
                    onValueChange = { specialty = it },
                    label = { Text("Specialty") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone Number") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                if (showError) {
                    Text(
                        text = "All fields are required!",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {
                        if (name.isBlank() || specialty.isBlank() || phone.isBlank()) {
                            showError = true
                        } else {
                            onAddDoctor(name.trim(), specialty.trim(), phone.trim())
                            name = ""
                            specialty = ""
                            phone = ""
                            showError = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DarkPink),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Contact", color = White)
                }
            }
        }
        Divider(modifier = Modifier.padding(vertical = 12.dp), thickness = 1.dp, color = MediumPink.copy(alpha = 0.3f))
        Text(
            text = "Saved Doctors",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (doctors.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No contacts saved yet.", color = Color.Gray)
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f, fill = false)
            ) {
                items(doctors) { doctor ->
                    EmergencyContactItem(doctor = doctor)
                }
            }
        }
    }
}



@Composable
fun EmergencyContactItem(doctor: Doctor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(MediumPink),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Contact Image",
                    tint = Color.Unspecified
                )
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = doctor.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Text(
                    text = doctor.specialty,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = doctor.phoneNumber,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
            Spacer(Modifier.width(86.dp))
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(Color.Red.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Message,
                    contentDescription = "Message",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(Color.Red.copy(alpha = 0.8f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Call",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

