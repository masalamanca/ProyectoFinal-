package com.example.mesadeayuda2.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TechnicianSupportScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mensaje de error de conexión
            Text(
                text = "Problemas de conexión con Google",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Red,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // Descripción del error
            Text(
                text = "Parece que hay un problema al intentar acceder a los servicios de Google.\n" +
                        "Verifique su red o intente más tarde.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            // Botón para intentar de nuevo
            Button(
                onClick = {
                    // Aquí podrías agregar la lógica para intentar reconectar a Google o manejar el error.
                    // Por ejemplo, reiniciar el proceso de autenticación de Google o mostrar otra pantalla.
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)) // Naranja
            ) {
                Text(text = "Intentar de nuevo", color = Color.White)
            }

            // Espaciador para separar el siguiente botón
            Spacer(modifier = Modifier.height(16.dp))

            // Botón para regresar al menú principal
            Button(
                onClick = {
                    navController.navigate("welcome") // Redirige a la pantalla de bienvenida (menú principal)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Verde
            ) {
                Text(text = "Regresar al Menú Principal", color = Color.White)
            }
        }
    }
}
