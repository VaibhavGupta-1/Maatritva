package com.example.maatritva.ui.introslider

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maatritva.ui.theme.LightYellow
import com.example.maatritva.ui.theme.Red40

@Composable
fun ButtonUi(
    text: String = "Next",
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    fontSize: Int = 14,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightYellow,
            contentColor = Red40
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            style = textStyle
        )
    }
}



