package com.carlosfren.teqruta
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carlosfren.teqruta.ui.theme.Camara_geolocalizacionTheme
import com.carlosfren.teqruta.viewmodel.ViajeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Camara_geolocalizacionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TeqRutaFormScreen(
                        onNavigateToLocation = {
                            startActivity(Intent(this, LocationActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeqRutaFormScreen(
    viewModel: ViajeViewModel = viewModel(),
    onNavigateToLocation: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TeqRuta - Registro de Traslado") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo Técnico con Validación
            OutlinedTextField(
                value = viewModel.tecnico,
                onValueChange = { viewModel.onTecnicoChanged(it) },
                label = { Text("Nombre del Técnico") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                isError = viewModel.errorTecnico != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (viewModel.errorTecnico != null) {
                Text(text = viewModel.errorTecnico!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            // Campo Clínica / Destino con Validación
            OutlinedTextField(
                value = viewModel.clinica,
                onValueChange = { viewModel.onClinicaChanged(it) },
                label = { Text("Clínica / Centro Destino") },
                leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                isError = viewModel.errorClinica != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (viewModel.errorClinica != null) {
                Text(text = viewModel.errorClinica!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            // Campo Kilometraje
            OutlinedTextField(
                value = viewModel.kilometraje,
                onValueChange = { viewModel.onKilometrajeChanged(it) },
                label = { Text("Kilómetros Recorridos") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = viewModel.errorKilometraje != null,
                modifier = Modifier.fillMaxWidth()
            )
            if (viewModel.errorKilometraje != null) {
                Text(text = viewModel.errorKilometraje!!, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            // Campo Peaje
            OutlinedTextField(
                value = viewModel.peaje,
                onValueChange = { viewModel.onPeajeChanged(it) },
                label = { Text("Monto Peaje ($)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Botón Registrar
            Button(
                onClick = {
                    if (viewModel.validarYGuardar()) {
                        // Formulario Validado para TeqRuta
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Traslado en Terreno")
            }

            // Botón para Recurso Nativo (GPS en Terreno)
            OutlinedButton(
                onClick = { onNavigateToLocation() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Registrar Ubicación GPS (Terreno)")
            }
        }
    }
}