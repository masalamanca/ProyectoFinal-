package com.example.mesadeayuda2.Entidades

// Archivo: User.kt

// Data class para representar a un usuario
data class User(
    val email: String, // Email del usuario
    val password: String, // Contraseña del usuario
    val role: String // Rol del usuario (ej. "tecnico" o "admin")
)
