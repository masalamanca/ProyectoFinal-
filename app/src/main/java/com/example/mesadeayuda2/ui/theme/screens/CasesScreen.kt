package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CasesScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)  // Fondo blanco
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Texto que dice "Casos"
            Text(
                text = "Casos",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.Black,
                    fontSize = 32.sp
                ),
                modifier = Modifier,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
