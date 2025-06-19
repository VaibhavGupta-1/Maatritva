package com.example.maatritva.ui.nutritions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.maatritva.ui.theme.Red40

@Composable
fun MonthlyScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        AppHeader("MonthWise Nutrition")
        // First row: Month 1, 2, 3
        Spacer(Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = { navController.navigate(("Weekly Screen1()")) },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 1", color = Color.White, fontSize = 18.sp) }
            Button(
                onClick = { navController.navigate("Weekly Screen2()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 2", color = Color.White, fontSize = 18.sp) }
            Button(
                onClick = { navController.navigate("Weekly Screen3()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 3", color = Color.White, fontSize = 18.sp) }
        }
        Spacer(Modifier.height(25.dp))

        // Second row: Month 4, 5, 6
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = { navController.navigate("Weekly Screen4()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 4", color = Color.White, fontSize = 18.sp) }
            Button(
                onClick = { navController.navigate("Weekly Screen5()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 5", color = Color.White, fontSize = 18.sp) }
            Button(
                onClick = { navController.navigate("Weekly Screen6()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 6", color = Color.White, fontSize = 18.sp) }
        }
        Spacer(Modifier.height(25.dp))

        // Third row: Month 7, 8, 9
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = { navController.navigate("Weekly Screen7()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 7", color = Color.White, fontSize = 18.sp) }
            Button(
                onClick = { navController.navigate("Weekly Screen8()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 8", color = Color.White, fontSize = 18.sp) }
            Button(
                onClick = { navController.navigate("Weekly Screen9()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 9", color = Color.White, fontSize = 18.sp) }
        }
        Spacer(Modifier.height(25.dp))

        // Fourth row: Month 10 centered
        Row(
            modifier = Modifier.padding(8.dp).padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { navController.navigate("Weekly Screen10()") },
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Red40)
            ) { Text(text = "Month 10", color = Color.White, fontSize = 18.sp) }
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
