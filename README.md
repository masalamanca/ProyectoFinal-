# Mesa de Ayuda 2.0

Mesa de Ayuda 2.0 es una aplicación de soporte técnico que permite a los técnicos gestionar los casos asignados, con funcionalidades como ver, modificar y actualizar el estado de los casos. La aplicación se implementa utilizando **Jetpack Compose** y **MVVM** en un proyecto de Android.

## Funcionalidades

1. **Gestión de Casos**: Los técnicos pueden ver una lista de casos, que incluyen detalles como el ID del caso, la descripción y el estado (Abierto, Pendiente, Resuelto).
2. **Cambio de Estado**: Los técnicos pueden cambiar el estado de un caso a "Resuelto" para indicar que el caso ha sido atendido.
3. **Pantalla de Casos**: La aplicación tiene una pantalla dedicada para ver los casos que están asignados al técnico.

Estructura 
 - app/
  - src/
    - main/
      - java/
        - com.example.mesadeayuda2/
          - entidades/          # Contiene las entidades (modelos) como Case.
          - screens/            # Contiene las pantallas de la app (UI).
          - viewmodel/          # Contiene los ViewModels, como TechnicianCasesViewModel.
      - res/
        - layout/              # Archivos XML de layout (En este caso NO).
        - values/              # Archivos de recursos como colores y cadenas.


## Requisitos

- **Android Studio** con soporte para **Jetpack Compose**.
- **Kotlin** (el lenguaje utilizado en el proyecto).
- **ViewModel** y **StateFlow** para la gestión de datos.
- 

## Instalación

Para ejecutar este proyecto en tu entorno local, sigue estos pasos:

# Aplicación Mesa de Ayuda IT

Este proyecto es una aplicación de **Mesa de Ayuda IT** que permite gestionar los problemas de soporte técnico de manera eficiente. Está desarrollada con **Jetpack Compose** y hace uso de **Jetpack Navigation** para manejar la navegación entre las pantallas. 

La aplicación está diseñada para permitir a los usuarios interactuar con un sistema de soporte técnico, con funcionalidades como registro, inicio de sesión y soporte técnico en caso de problemas de conexión con los servicios de Google.

## Características

- **Pantalla de Bienvenida**: Contiene opciones para registrarse, iniciar sesión como usuario, como técnico de soporte o como administrador.
- **Pantalla de Soporte Técnico**: Muestra un mensaje de error cuando hay un problema de conexión con los servicios de Google, permitiendo al usuario intentar nuevamente o regresar al menú principal.
- **Navegación Fluida**: La navegación entre pantallas se maneja de manera sencilla y eficiente utilizando **Jetpack Navigation**.
- **Diseño Moderno**: Utiliza **Jetpack Compose** para construir interfaces modernas y reactivas.

## Tecnologías Usadas

- **Kotlin**: Lenguaje de programación utilizado para desarrollar la aplicación.
- **Jetpack Compose**: Framework de UI para crear interfaces de usuario de manera declarativa.
- **Jetpack Navigation**: Para manejar la navegación entre pantallas de manera sencilla.
- **Material3**: Para la UI y componentes de diseño (botones, texto, etc.).

## Estructura del Proyecto

La aplicación está organizada en diferentes paquetes, donde cada parte del sistema (pantallas, navegación, temas) tiene su propia estructura modular. 

### Estructura de carpetas:

- **`com.example.mesadeayuda2`**: Paquete principal que contiene la lógica de la aplicación.
  - **`screens`**: Contiene los archivos de las pantallas de la aplicación.
    - `WelcomeScreen.kt`: Pantalla principal que muestra los botones para acceder a las diferentes opciones (registro, login de usuario, login de técnico de soporte, y login de administrador).
    - `TechnicianSupportScreen.kt`: Pantalla que muestra un mensaje de error cuando hay problemas de conexión con los servicios de Google.
  - **`ui.theme`**: Archivos relacionados con la personalización de la UI, como colores, tipografía y otros recursos visuales.
  - **`NavGraph.kt`**: Contiene la configuración de las rutas de navegación de la aplicación.
  - **`ui.theme`**: Definición del tema de la aplicación, colores y tipografías.

## Funcionalidades

### Pantalla de Bienvenida (`WelcomeScreen.kt`)

La pantalla de bienvenida muestra las siguientes opciones:

- **Logo de la aplicación**: Se encuentra centrado en la parte superior de la pantalla.
- **Título**: Un mensaje de bienvenida al usuario.
- **Botones**:
  - **Registrarse**: Permite a los usuarios registrarse en la plataforma.
  - **Usuario**: Permite a los usuarios iniciar sesión como usuarios regulares.
  - **Técnico de Soporte**: Permite a los técnicos de soporte iniciar sesión en la aplicación.
  - **Administrador**: Permite a los administradores iniciar sesión.

#### Código de la Pantalla de Bienvenida:
```kotlin
@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Imagen de fondo
        Image(
            painter = backgroundImage,
            contentDescription = "Fondo de pantalla",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                painter = logoImage,
                contentDescription = "Logo de inicio",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            // Título
            Text(
                text = "Bienvenido a Mesa de Ayuda IT",
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = Color.White,
                    fontSize = 40.sp
                ),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )

            // Botones de acción
            Button(
                onClick = { navController.navigate("register") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
            ) {
                Text(text = "Registrarse", color = Color.White)
            }

            // Botón de Ingreso
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
            ) {
                Text(text = "Usuario", color = Color.White)
            }

            // Botón para técnico de soporte
            Button(
                onClick = { navController.navigate("login_technician") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
            ) {
                Text(text = "Técnico de Soporte", color = Color.White)
            }

            // Botón para Administrador
            Button(
                onClick = { navController.navigate("admin_login") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5722))
            ) {
                Text(text = "Administrador", color = Color.White)
            }
        }
    }
}



git clone https://github.com/tuusuario/mesadeayuda2.git
#   P r o y e c t o F i n a l - 
 
 
