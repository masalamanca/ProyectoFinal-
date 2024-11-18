package com.example.MesaAyudaFinal.ui.theme.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mesadeayuda2.ViewModel.UserViewModel

@Composable
fun AdminLoginSuccessScreen() {
    val userViewModel: UserViewModel = viewModel()
    val users by userViewModel.users
    val roleAssignmentMessage by userViewModel.roleAssignmentMessage // Observa el estado del mensaje

    // Cargar los usuarios al iniciar la pantalla
    LaunchedEffect(Unit) {
        userViewModel.loadUsers()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF6200EE), Color(0xFFBB86FC)) // Degradado de colores
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally // Centrar el contenido
        ) {
            // Título centrado y con mayor tamaño
            Text(
                text = "Lista de Usuarios Registrados:",
                style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp), // Aumenté el tamaño de la fuente
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Center // Centrado
            )

            // Mostrar el mensaje de asignación de rol si está presente
            roleAssignmentMessage?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (it.startsWith("Error")) Color.Red else Color.White, // Blanco cuando es exitoso
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center // Centrado del mensaje
                )
            }

            // Mostrar la lista de usuarios
            if (users.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(users) { user ->
                        UserItem(user.name, user.email, user.id, user.role, userViewModel)
                    }
                }
            } else {
                Text(
                    text = "No hay usuarios registrados.",
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun UserItem(name: String, email: String, userId: String, role: String, userViewModel: UserViewModel) {
    var isSelected by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { isSelected = !isSelected },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Nombre: $name", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Correo: $email", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Rol actual: $role", style = MaterialTheme.typography.bodyMedium)

            // Mostrar botones solo si la tarjeta está seleccionada
            if (isSelected) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            // Lógica para asignar el rol de "Usuario" al usuario
                            userViewModel.assignRole(userId, "Usuario")
                        },
                        modifier = Modifier.weight(1f).padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Asignar como Usuario",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = "Usuario", modifier = Modifier.padding(start = 4.dp))
                    }

                    Button(
                        onClick = {
                            // Lógica para asignar el rol de "Técnico" al usuario
                            userViewModel.assignRole(userId, "Técnico")
                        },
                        modifier = Modifier.weight(1f).padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = "Asignar como Técnico",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = "Técnico", modifier = Modifier.padding(start = 4.dp))
                    }
                }
            }
        }
    }
}
