package com.example.maatritva.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChildFriendly
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maatritva.ui.homescreen.AppHeader
import com.example.maatritva.ui.theme.DarkPink
import com.example.maatritva.ui.theme.LightPink
import com.example.maatritva.ui.theme.MediumPink
import com.example.maatritva.ui.theme.Pink40
import com.example.maatritva.ui.theme.Pink80
import com.example.maatritva.ui.theme.Red40
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("MissingPermission")
@Composable
fun Hospital() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(locationPermissionState.status) {
        if (locationPermissionState.status.isGranted) {
            try {
                val location: Location? = fusedLocationClient.lastLocation.await()
                if (location != null) {
                    userLocation = LatLng(location.latitude, location.longitude)
                } else {
                    error = "Unable to fetch location."
                }
            } catch (e: Exception) {
                error = "Error fetching location: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5)),
//        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader1()

        Box(modifier = Modifier
            .height(350.dp).padding(16.dp), contentAlignment = Alignment.Center) {
            when {
                isLoading -> CircularProgressIndicator()
                error != null -> Text(text = error!!)
                userLocation != null -> {
                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(userLocation!!, 15f)
                    }
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState,
                        properties = MapProperties(isMyLocationEnabled = true)
                    ) {
                        Marker(
                            state = MarkerState(position = userLocation!!),
                            title = "You are here"
                        )
                        Marker(
                            state = MarkerState(
                                position = LatLng(userLocation!!.latitude + 0.002, userLocation!!.longitude + 0.002)
                            ),
                            title = "Om Maternity Center",
                            snippet = "Example Hospital-1"
                        )
                        Marker(
                            state = MarkerState(
                                position = LatLng(userLocation!!.latitude + 0.000, userLocation!!.longitude + 0.006)
                            ),
                            title = "Uttam Hospital",
                            snippet = "Example Hospital-2"
                        )
                    }
                }
            }
        }
        Text("Nearby",modifier = Modifier.padding(horizontal = 16.dp,10.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DarkPink)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                    imageVector = Icons.Default.LocationOn,
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
                    text = "Uttam Hospital",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Jankipuam Extension, Lucknow",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        Color(0xFFD33560),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
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
                        text = "Om Maternity Center",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "South City (Raebarely Road), Lucknow",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
        }

    }

}


@Composable
fun AppHeader1() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Red40)
            .padding(top = 50.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Nearest Hospitals",
            color = Color.White,
            fontSize = 22.sp
        )
    }
}