package com.example.maatritva.ui.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.maatritva.ui.homescreen.BottomNavigationBar
import com.example.maatritva.ui.theme.Red40

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, navController: NavHostController) {
    val profileState by viewModel.profileState.collectAsState()
    var name by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedBottomItem by remember { mutableIntStateOf(2) } // Profile tab
    var isEditing by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) imageUri = uri
    }

    // Load profile from DB on first launch
    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    // Always update fields from DB
    LaunchedEffect(profileState) {
        if (profileState != null) {
            name = profileState!!.name
            dob = profileState!!.dob
            gender = profileState!!.gender
            imageUri = profileState!!.imageUri?.toUri()
        }
    }

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            BottomNavigationBar(
                selectedItem = selectedBottomItem,
                navController = navController,
                onItemSelected = { selectedBottomItem = it }
            )
        },
        floatingActionButton = {
            if (!isEditing) {
                FloatingActionButton(
                    onClick = { isEditing = true },
                    containerColor = Red40
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Profile")
                }
            }
        },
        floatingActionButtonPosition = androidx.compose.material3.FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(bottom = paddingValues.calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AppHeader("Profile")
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.BottomEnd) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    if (imageUri != null) {
                        AsyncImage(
                            model = imageUri,
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(120.dp),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(64.dp),
                            tint = Color.White
                        )
                    }
                }
                // Edit icon for image
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Image",
                    modifier = Modifier
                        .size(32.dp)
                        .offset(y = 16.dp)
                        .background(Color.White, CircleShape)
                        .clip(CircleShape)
                        .clickable { pickImageLauncher.launch("image/*") }
                        .padding(6.dp),
                    tint = Red40
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                enabled = isEditing
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = dob,
                onValueChange = { dob = it },
                label = { Text("Date of Birth (yyyy-mm-dd)") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                enabled = isEditing
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Gender") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                enabled = isEditing
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (isEditing) {
                Button(
                    onClick = {
                        viewModel.saveProfile(name, dob, gender, imageUri?.toString())
                        isEditing = false
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                ) {
                    Text("Save Profile")
                }
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
            .padding(top = 30.dp)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = text,
            color = textColor,
            fontSize = 22.sp
        )
    }
}