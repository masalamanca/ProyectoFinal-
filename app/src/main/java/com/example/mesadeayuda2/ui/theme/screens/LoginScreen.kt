package com.example.MesaAyudaFinal.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mesadeayuda2.ViewModel.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val loginViewModel: LoginViewModel = viewModel()
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val isAuthenticated by remember { derivedStateOf { loginViewModel.isAuthenticated } }
    val errorMessage by remember { derivedStateOf { loginViewModel.errorMessage } }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Función para validar los campos
    fun validateFields(): Boolean {
        var isValid = true

        // Validación del nombre de usuario
        if (username.isBlank()) {
            usernameError = "El nombre de usuario es obligatorio"
            isValid = false
        } else {
            usernameError = ""
        }

        // Validación del correo electrónico
        if (email.isBlank()) {
            emailError = "El correo electrónico es obligatorio"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Correo electrónico inválido"
            isValid = false
        } else {
            emailError = ""
        }

        // Validación de la contraseña
        if (password.isBlank()) {
            passwordError = "La contraseña es obligatoria"
            isValid = false
        } else {
            passwordError = ""
        }

        return isValid
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF6200EE), Color(0xFFBB86FC))
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Iniciar sesión",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Campo de nombre de usuario
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                isError = usernameError.isNotEmpty(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.LightGray
                )
            )
            if (usernameError.isNotEmpty()) {
                Text(text = usernameError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }

            // Campo de correo electrónico
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                isError = emailError.isNotEmpty(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { /* Acción para el siguiente campo */ }),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.LightGray
                )
            )
            if (emailError.isNotEmpty()) {
                Text(text = emailError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }

            // Campo de contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña", color = Color.White) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            tint = Color.White
                        )
                    }
                },
                isError = passwordError.isNotEmpty(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.LightGray,
                    cursorColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.LightGray
                )
            )
            if (passwordError.isNotEmpty()) {
                Text(text = passwordError, color = Color.Red, style = MaterialTheme.typography.bodySmall)
            }

            // Botón de inicio de sesión
            Button(
                onClick = {
                    if (validateFields()) {
                        loginViewModel.authenticateUser(email, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722)), // Color atractivo
                contentPadding = PaddingValues(vertical = 8.dp) // Ajusta el alto del botón al contenido
            ) {
                // Icono y texto en el botón
                Icon(
                    imageVector = Icons.Filled.Login, // Ícono de login
                    contentDescription = "Ingresar",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 8.dp) // Espacio entre el ícono y el texto
                )
                Text("Ingresar", color = Color.White, style = MaterialTheme.typography.bodyLarge)
            }

            // Mensaje de error
            if (errorMessage != null) {
                Text(
                    text = "Error: $errorMessage",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Navegación y mensaje de éxito
            if (isAuthenticated) {
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Ingreso exitoso")
                    }

                    navController.navigate("login_success/${username}") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }

            // Host para mostrar el Snackbar
            SnackbarHost(hostState = snackbarHostState)
        }
    }
}
