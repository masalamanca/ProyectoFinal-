package com.example.mesadeayuda2.ui.theme.screens

import android.app.DatePickerDialog
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

@Composable
fun NewCaseScreen() {
    var date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var sedeSolicitante by remember { mutableStateOf("") }
    var tipoSolicitud by remember { mutableStateOf("") }
    var tipoSolicitudOtro by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }
    var imageUri: Uri? by remember { mutableStateOf(null) }
    var nombreUsuario by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = FirebaseFirestore.getInstance()

    val sedes = listOf("Calera", "Guasca", "Sopó", "Planta de Producción", "Cajicá", "Chía", "Multiparque", "Calle 80", "Tenjo")
    val tiposDeSolicitud = listOf(
        "Acceso",
        "Cambio",
        "Nuevas Instalaciones",
        "Actualización de Software",
        "Recuperación de Datos",
        "Capacitación o Formación",
        "Incidencias de Seguridad",
        "Creación de Informes",
        "Aumento de Recursos",
        "Nuevas Herramientas",
        "Eliminación de Software",
        "Migración de Datos",
        "Otro"
    )

    var expandedSede by remember { mutableStateOf(false) }
    var expandedSolicitud by remember { mutableStateOf(false) }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        },
        year, month, day
    )

    datePickerDialog.datePicker.minDate = System.currentTimeMillis()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    // Fondo degradado
    val gradientBackground = Brush.linearGradient(
        colors = listOf(Color(0xFFFFBB86FC), Color(0xFFFF6200EE)),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground) // Aquí se aplica el fondo degradado
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Registrar Nuevo Caso",
            style = MaterialTheme.typography.h5.copy(color = Color.White), // Cambié el color del texto a blanco para que resalte
            modifier = Modifier.padding(bottom = 20.dp)
        )

        OutlinedTextField(
            value = nombreUsuario,
            onValueChange = { nombreUsuario = it },
            label = { Text("Nombre de Usuario", color = Color.White) }, // Color de la etiqueta en blanco
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Ícono de Usuario",
                    tint = Color.White // Color del ícono en blanco
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White, // Color del texto en blanco
                backgroundColor = Color.Transparent, // Fondo transparente para el cuadro de texto
                focusedBorderColor = Color.White, // Borde blanco cuando está enfocado
                unfocusedBorderColor = Color.White // Borde blanco cuando no está enfocado
            )
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = date,
                onValueChange = { },
                label = { Text("Fecha", color = Color.White) }, // Color de la etiqueta en blanco
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clickable { datePickerDialog.show() },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Event,
                        contentDescription = "Seleccionar Fecha",
                        modifier = Modifier.clickable { datePickerDialog.show() },
                        tint = Color.White // Color del ícono en blanco
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White, // Color del texto en blanco
                    backgroundColor = Color.Transparent, // Fondo transparente para el cuadro de texto
                    focusedBorderColor = Color.White, // Borde blanco cuando está enfocado
                    unfocusedBorderColor = Color.White // Borde blanco cuando no está enfocado
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = sedeSolicitante,
                onValueChange = { },
                label = { Text("Sede Solicitante", color = Color.White) }, // Color de la etiqueta en blanco
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clickable { expandedSede = true },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expandedSede = !expandedSede }) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Seleccionar Sede",
                            tint = Color.White // Color del ícono en blanco
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White, // Color del texto en blanco
                    backgroundColor = Color.Transparent, // Fondo transparente para el cuadro de texto
                    focusedBorderColor = Color.White, // Borde blanco cuando está enfocado
                    unfocusedBorderColor = Color.White // Borde blanco cuando no está enfocado
                )
            )

            DropdownMenu(
                expanded = expandedSede,
                onDismissRequest = { expandedSede = false }
            ) {
                sedes.forEach { sede ->
                    DropdownMenuItem(
                        onClick = {
                            sedeSolicitante = sede
                            expandedSede = false
                        },
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(text = sede, color = Color.Black) // Color del texto en blanco en el dropdown
                    }
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = tipoSolicitud,
                onValueChange = { },
                label = { Text("Tipo de Solicitud", color = Color.White) }, // Color de la etiqueta en blanco
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clickable { expandedSolicitud = true },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { expandedSolicitud = !expandedSolicitud }) {
                        Icon(
                            imageVector = Icons.Default.ListAlt,
                            contentDescription = "Seleccionar Tipo de Solicitud",
                            tint = Color.White // Color del ícono en blanco
                        )
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Color.White, // Color del texto en blanco
                    backgroundColor = Color.Transparent, // Fondo transparente para el cuadro de texto
                    focusedBorderColor = Color.White, // Borde blanco cuando está enfocado
                    unfocusedBorderColor = Color.White // Borde blanco cuando no está enfocado
                )
            )

            DropdownMenu(
                expanded = expandedSolicitud,
                onDismissRequest = { expandedSolicitud = false }
            ) {
                tiposDeSolicitud.forEach { tipo ->
                    DropdownMenuItem(
                        onClick = {
                            tipoSolicitud = tipo
                            expandedSolicitud = false
                        },
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(text = tipo, color = Color.Black) // Color del texto en blanco en el dropdown
                    }
                }
            }
        }

        if (tipoSolicitud == "Otro") {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = tipoSolicitudOtro,
                    onValueChange = {
                        tipoSolicitudOtro = it.copy(text = it.text.take(50))
                    },
                    label = { Text("Especifique Otro Tipo de Solicitud", color = Color.White) }, // Color de la etiqueta en blanco
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    maxLines = 2,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.White, // Color del texto en blanco
                        backgroundColor = Color.Transparent, // Fondo transparente para el cuadro de texto
                        focusedBorderColor = Color.White, // Borde blanco cuando está enfocado
                        unfocusedBorderColor = Color.White // Borde blanco cuando no está enfocado
                    )
                )

                Text(
                    text = "${50 - tipoSolicitudOtro.text.length} caracteres restantes",
                    style = MaterialTheme.typography.body2.copy(color = Color.White),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it.copy(text = it.text.take(200))
            },
            label = { Text("Descripción Detallada", color = Color.White) }, // Color de la etiqueta en blanco
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            maxLines = 5,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Description,
                    contentDescription = "Descripción Detallada",
                    tint = Color.White // Color del ícono en blanco
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White, // Color del texto en blanco
                backgroundColor = Color.Transparent, // Fondo transparente para el cuadro de texto
                focusedBorderColor = Color.White, // Borde blanco cuando está enfocado
                unfocusedBorderColor = Color.White // Borde blanco cuando no está enfocado
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "${200 - description.text.length} caracteres restantes",
                style = MaterialTheme.typography.body2.copy(color = Color.White),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { galleryLauncher.launch("image/*") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(48.dp)
                .background(MaterialTheme.colors.primary, shape = RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary)
        ) {
            Icon(
                imageVector = Icons.Default.Image,
                contentDescription = "Seleccionar Imagen",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Seleccionar Imagen", color = Color.White)
        }

        imageUri?.let { uri ->
            Image(painter = rememberImagePainter(uri), contentDescription = null, modifier = Modifier.size(200.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val caseData = hashMapOf(
                    "nombreUsuario" to nombreUsuario,
                    "fecha" to date,
                    "descripcion" to description.text,
                    "sedeSolicitante" to sedeSolicitante,
                    "tipoSolicitud" to tipoSolicitud,
                    "tipoSolicitudOtro" to tipoSolicitudOtro.text,
                    "imageUri" to imageUri.toString(),
                    "estado" to "Abierto"  // Estado predeterminado
                )

                db.collection("cases").add(caseData)
                    .addOnSuccessListener {
                        Log.d("NewCase", "Caso registrado correctamente")
                        // Limpiar campos
                        nombreUsuario = ""
                        date = ""
                        description = TextFieldValue("")
                        sedeSolicitante = ""
                        tipoSolicitud = ""
                        tipoSolicitudOtro = TextFieldValue("")
                        imageUri = null
                        errorMessage = ""
                    }
                    .addOnFailureListener { e ->
                        errorMessage = "Error al registrar el caso: ${e.message}"
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Crear Caso")
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
