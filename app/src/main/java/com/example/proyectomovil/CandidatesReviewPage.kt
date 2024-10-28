package com.example.proyectomovil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.font.FontWeight
import com.example.proyectomovil.ui.theme.ProyectoMovilTheme

class CandidatesReviewPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoMovilTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OutlinedCardExample(rememberNavController())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedCardExample(
    navController: NavController,
    respuesta1: String? = null,
    respuesta2: String? = null,
    respuesta3: String? = null,
    respuesta4: String? = null,
    respuesta5: String? = null
) {
    var selectedUser by remember { mutableStateOf("Usuario 1") } // Usuario predeterminado
    val users = listOf("Usuario 1", "Usuario 2", "Usuario 3", "Usuario 4", "Usuario 5")
    val responses = mapOf(
        "Usuario 1" to listOf(
            "1. ¿Qué haces si un código no está documentado?" to (respuesta1 ?: "Respuesta no disponible"),
            "2. ¿Qué lenguaje escogerías para trabajar en bases de datos y por qué?" to (respuesta2 ?: "Respuesta no disponible"),
            "3. Cuando declaras variables, ¿bajo qué términos prefieres crearlas?" to (respuesta3 ?: "Respuesta no disponible"),
            "4. ¿Bajo qué rol te desempeñas mejor en el desarrollo de proyectos y por qué?" to (respuesta4 ?: "Respuesta no disponible"),
            "5. Cuando desarrollas software, ¿qué es lo más importante a tener en cuenta y por qué?" to (respuesta5 ?: "Respuesta no disponible")
        )
        // Otros usuarios pueden tener respuestas en el futuro
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Revisión de Postulados") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("candidatesList") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seleccione un usuario:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Botones de selección de usuarios
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                users.forEach { user ->
                    Button(
                        onClick = { selectedUser = user },
                        modifier = Modifier.padding(horizontal = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text(text = user, color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tarjeta para mostrar las respuestas del usuario seleccionado
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Respuestas de $selectedUser:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Mostrar las respuestas del usuario seleccionado
                    responses[selectedUser]?.forEach { (question, answer) ->
                        Text(
                            text = question,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = answer,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

