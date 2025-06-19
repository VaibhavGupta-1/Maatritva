package com.example.maatritva.ui.nutritions.weekly

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maatritva.ui.theme.Red40
import androidx.navigation.NavHostController

@Composable
fun WeeklyScreen1(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd1") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 1", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd2") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 2", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd3") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 3", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd4") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 4", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen2(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd5") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 5", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd6") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 6", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd7") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 7", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd8") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 8", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen3(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd9") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 9", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd10") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 10", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd11") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 11", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd12") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 12", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen4(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd13") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 13", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd14") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 14", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd15") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 15", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd16") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 16", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen5(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd17") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 17", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd18") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 18", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd19") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 19", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd20") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 20", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen6(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd21") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 21", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd22") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 22", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd23") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 23", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd24") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 24", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen7(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd25") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 25", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd26") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 26", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd27") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 27", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd28") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 28", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen8(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd29") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 29", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd30") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 30", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd31") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 31", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd32") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 32", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen9(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd33") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 33", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd34") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 34", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd35") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 35", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd36") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 36", color = Color.White, fontSize = 18.sp) }
    }
}

@Composable
fun WeeklyScreen10(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { navController.navigate("weekd37") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 37", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd38") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 38", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd39") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 39", color = Color.White, fontSize = 18.sp) }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("weekd40") },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Red40)
        ) { Text("Week 40", color = Color.White, fontSize = 18.sp) }
    }
}

