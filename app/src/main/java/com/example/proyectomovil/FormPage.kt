package com.example.proyectomovil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyectomovil.ui.theme.ProyectoMovilTheme

val CustomBlue = Color(0xFF3F51B5)
val BackgroundColor = Color(0xFF2E3140)

@Composable
fun Form(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(36.dp)
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "PRUEBA TÉCNICA DEL SOFTWARE",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    var respuesta1 by remember { mutableStateOf("") }
                    var respuesta2 by remember { mutableStateOf("") }
                    var respuesta3 by remember { mutableStateOf("") }
                    var respuesta4 by remember { mutableStateOf("") }
                    var respuesta5 by remember { mutableStateOf("") }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        item {
                            Text(
                                text = "1. ¿Qué haces si un código no está documentado?",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            BasicTextField(
                                value = respuesta1,
                                onValueChange = { respuesta1 = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Next
                                )
                            )

                            Text(
                                text = "2. ¿Qué lenguaje escogerías para trabajar en bases de datos y por qué?",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            BasicTextField(
                                value = respuesta2,
                                onValueChange = { respuesta2 = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Next
                                )
                            )

                            Text(
                                text = "3. Cuando declaras variables, ¿bajo qué términos prefieres crearlas?",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            BasicTextField(
                                value = respuesta3,
                                onValueChange = { respuesta3 = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done
                                )
                            )

                            Text(
                                text = "4. ¿Bajo qué rol te desempeñas mejor en el desarrollo de proyectos y por qué?",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            BasicTextField(
                                value = respuesta4,
                                onValueChange = { respuesta4 = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done
                                )
                            )

                            Text(
                                text = "5. Cuando desarrollas software, ¿qué es lo más importante a tener en cuenta y por qué?",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            BasicTextField(
                                value = respuesta5,
                                onValueChange = { respuesta5 = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                                    .background(Color.LightGray, RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                keyboardOptions = KeyboardOptions.Default.copy(
                                    imeAction = ImeAction.Done
                                )
                            )

                            Button(
                                onClick = {
                                    navController.navigate(
                                        "candidatesReviewPage?respuesta1=${respuesta1}&respuesta2=${respuesta2}&respuesta3=${respuesta3}&respuesta4=${respuesta4}&respuesta5=${respuesta5}"
                                    )
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp)
                            ) {
                                Text(text = "Enviar respuestas")
                            }
                        }
                    }
                }
            }
        }
    }
}
