package com.example.maatritva.ui

import android.annotation.SuppressLint
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.*
import kotlinx.coroutines.launch
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

// Define the color palette

// Entity
@Entity(tableName = "doctors")
data class Doctor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val specialty: String,
    val phoneNumber: String
)

// DAO
@Dao
interface DoctorDao {
    @Insert
    suspend fun insert(doctor: Doctor)

    @Update
    suspend fun update(doctor: Doctor)

    @Delete
    suspend fun delete(doctor: Doctor)

    @Query("SELECT * FROM doctors ORDER BY name ASC")
    fun getAllDoctors(): LiveData<List<Doctor>>
}

// Database
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
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Repository
class DoctorRepository(private val doctorDao: DoctorDao) {
    val allDoctors: LiveData<List<Doctor>> = doctorDao.getAllDoctors()

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

// ViewModel
class DoctorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DoctorRepository
    val allDoctors: LiveData<List<Doctor>>

    init {
        val doctorDao = AppDatabase.getDatabase(application).doctorDao()
        repository = DoctorRepository(doctorDao)
        allDoctors = repository.allDoctors
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

// UI Components
@Composable
fun EmergencyContactScreen(doctorViewModel: DoctorViewModel = viewModel()) {
    val doctors by doctorViewModel.allDoctors.observeAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Emergency Contacts",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        items(doctors) { doctor ->
            EmergencyContactItem(doctor = doctor)
        }

        item {
            Text(
                text = "Quick Actions",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Handle call action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = DarkPink),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Call",
                        tint = White
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Call Now", color = White)
                }

                Spacer(Modifier.width(8.dp))

                Button(
                    onClick = { /* Handle message action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Message,
                        contentDescription = "Message",
                        tint = Color.Black
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Message", color = Color.Black)
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
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
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
                    painter = painterResource(id = R.drawable.ic_placeholder),
                    contentDescription = "Contact Image",
                    tint = Color.Unspecified
                )
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(text = doctor.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = doctor.specialty, fontSize = 14.sp, color = Color.Gray)
                Text(text = doctor.phoneNumber, fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}

// Preview
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun PreviewEmergencyContactScreen() {
    val context = LocalContext.current
    val doctorViewModel = DoctorViewModel(context.applicationContext as Application)
    EmergencyContactScreen(doctorViewModel)
}
